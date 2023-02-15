package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.example.demo.common.LayUIResponseBo;
import com.example.demo.common.RestResponseBo;
import com.example.demo.dao.InfoTableDao;
import com.example.demo.dao.NoticeTableDao;
import com.example.demo.entity.EventTable;
import com.example.demo.entity.MemberTable;
import com.example.demo.entity.NoticeTable;
import com.example.demo.service.IEventService;
import com.example.demo.service.IPositionService;
import com.example.demo.service.IUserService;
import com.example.demo.utils.WebConst;
import com.example.demo.vo.MemberVo;
import com.example.demo.vo.MemberVo3;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/11/30/22:31
 */
@Controller
public class IndexController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    IEventService eventService;

    @Autowired
    IUserService userService;

    @Autowired
    NoticeTableDao noticeTableDao;

    @Autowired
    InfoTableDao infoTableDao;

    @Autowired
    IPositionService positionService;

    @GetMapping(value = "")
    public String index(HttpServletRequest request, Model model){
        LOGGER.info("Enter homePage index method");
        List<NoticeTable> noticeTables = noticeTableDao.selectList(null);
        model.addAttribute("notices", noticeTables);
        String clubProfile = infoTableDao.queryByInfoName("社团简介");
        String clubRule = infoTableDao.queryByInfoName("社团条例");
        model.addAttribute("club_profile", clubProfile);
        model.addAttribute("club_rule", clubRule);
        return "index";
    }

    @GetMapping(value = "/index")
    public String redirectIndex(HttpServletRequest request, Model model){
        LOGGER.info("Enter homePage index redirect method");
        return "redirect:/";
    }

    // 获取进行中的事件
    @GetMapping(value = "/event/detail")
    public String eventDetail(Model model){
        LOGGER.info("Enter eventDetail method");
        List<EventTable> events = eventService.getGoingEventTableList();
        model.addAttribute("events", events);
        return "event_detail";
    }

    @GetMapping(value = "/event/search/{keywords}")
    public String eventSearch(Model model, @PathVariable String keywords){
        LOGGER.info("Enter eventSearch method");
        List<EventTable> events = eventService.searchEventList(keywords);
        model.addAttribute("events", events);
        return "event_detail";
    }

    @PostMapping(value = "event/detail/signup")
    @ResponseBody
    public RestResponseBo eventSignup(@RequestParam int event_id, @RequestParam int user_id, HttpServletRequest req){
        LOGGER.info("Enter eventSignup method");
        boolean ok = eventService.signup(event_id, user_id);
        if(ok){
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail("已报名");
    }

    @GetMapping(value = "/register")
    public String register(){
        return "register";
    }

    /**
     * 1. 获取所需数据
     * 2. 查QQ是否重复(可省略?)   重复，返回fail
     * 3. 设置默认数据，合并获取数据，插入member_table
     * 4. 看插入是否ok，来返回
     * @param reqm
     * @return
     */
    @PostMapping(value = "register")
    @ResponseBody
    public RestResponseBo toRegister(@RequestBody Map reqm){
        LOGGER.info("Enter toRegister method");
        System.out.println("Map"+reqm.toString());
        String qq = (String) reqm.get("qq");
        // 查看QQ号是否已经注册
        MemberTable oldMember = userService.queryUserByQQ(qq);
        if(oldMember != null){
            return RestResponseBo.fail("qq号已经注册了");
        }
        MemberTable newMember = initNewMember(reqm);
        // 插入数据库
        int is_ok = userService.getBaseMapper().insert(newMember);
        if(is_ok == 1) {
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail("数据库出错");
    }

    public MemberTable initNewMember(Map reqm){
        MemberTable newMember = new MemberTable();
        // 默认设置, id、 creat_time、update_time自动改
        newMember.setPosition_id(9);
        newMember.setIn_club((byte) 2);
        // 合并获取数据
        newMember.setUser_name((String) reqm.get("user_name"));
        newMember.setUser_number((String) reqm.get("user_number"));
        newMember.setGender(Byte.parseByte((String) reqm.get("gender")));
        newMember.setClass_name((String) reqm.get("class_name"));
        newMember.setTelephone((String) reqm.get("telephone"));
        newMember.setQq((String) reqm.get("qq"));
        newMember.setPassword((String) reqm.get("password"));
        // 不能直接使用int强转
        newMember.setDepartment_id( Integer.parseInt((String) reqm.get("department_id")));
        newMember.setProfile((String) reqm.get("profile"));
        return newMember;
    }

    /**
     * 应该被shiro拦截，要求登录
     * @return
     */
    @GetMapping(value = "/member")
    public String memberLookup(){
        return "member_lookup";
    }

    /**
     *
     * @param session
     *  用session检查是否登录，即是否是成员
     * @return
     */
    @PostMapping(value = "member")
    @ResponseBody
    public Object memberList(HttpSession session){
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail("未登录");
        }
        List<MemberTable> memberTableList = userService.getInClubMemberList();
        memberTableList.addAll(userService.getPastMemberList());
        List<MemberVo> memberVos = new ArrayList();
        for(MemberTable m:memberTableList){
            memberVos.add(new MemberVo(m, positionService.getById(m.getPosition_id()).getPosition_name()));
        }
        List<Map> resMap = JSON.parseArray(JSON.toJSONString(memberVos), Map.class);
        System.out.println("resMap"+resMap);
        return LayUIResponseBo.ok(resMap);
    }

    @GetMapping(value = "/noauth")
    public String noauth(){
        return "error/noauth";
    }

    @GetMapping(value = "/event/unchecked")
    public String eventUncheckedLookup(){
        return "signup_event_lookup";
    }

    @PostMapping(value = "event/unchecked")
    @ResponseBody
    public Object eventUncheckedList(HttpSession session){
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail("未登录");
        }
        MemberTable user = (MemberTable) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        int userId = user.getId(); //通过useId去查事件
        // 获取该user的所有未签到事件
        List<Map> resMap = JSON.parseArray(JSON.toJSONString(eventService.eventUncheckedList(userId)), Map.class);
        System.out.println("resMap"+resMap);
        return LayUIResponseBo.ok(resMap);
    }

    //// 请假需要重新设计
    //@PostMapping(value = "event/off")
    //@ResponseBody
    //public Object eventOff(HttpSession session, @RequestBody Map reqm){
    //    if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
    //        return LayUIResponseBo.fail("未登录");
    //    }
    //    // 不能使用int强转
    //    int eventId = Integer.parseInt(reqm.get("event_id").toString());
    //    MemberTable user = (MemberTable) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
    //    int userId = user.getId();
    //    System.out.println("off event_id:"+reqm);
    //    // 获取该user的所有未签到事件
    //    List<Map> resMap = JSON.parseArray(JSON.toJSONString(eventService.eventUncheckedList(userId)), Map.class);
    //    System.out.println("resMap"+resMap);
    //    return LayUIResponseBo.ok(resMap);
    //}

    @PostMapping(value = "event/infomation")
    @ResponseBody
    public Object eventInfomation(HttpSession session, @RequestBody Map reqm){
        LOGGER.info("Enter eventInfomation method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail("未登录");
        }
        // 不能使用int强转
        int eventId = Integer.parseInt(reqm.get("event_id").toString());
        MemberTable user = (MemberTable) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        System.out.println("event_id:"+reqm);
        // 获取该user的所有未签到事件
        Map<String, String> resMap = new HashMap();
        resMap.put("detail", eventService.getBaseMapper().selectById(eventId).getDetail());
        System.out.println("resMap"+resMap);
        return LayUIResponseBo.ok(resMap);
    }

    @GetMapping(value = "/member/self")
    public String selfLook(HttpSession session, Model model){
        LOGGER.info("Enter eventInfomation method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return "redirect:/user/login";
        }
        //MemberTable loginUser = (MemberTable)session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        ////int userId = loginUser.getId();
        //String positionName = positionService.getBaseMapper().selectById(loginUser.getPosition_id()).getPosition_name();
        //MemberVo3 selfInfo = new MemberVo3(loginUser, positionName);
        //Map resMap = JSON.parseObject(JSON.toJSONString(selfInfo), Map.class);
        //model.addAttribute("selfInfo", resMap);
        //System.out.println("selfInfo"+resMap);
        return "self_form";
    }

    @PostMapping(value = "/member/self")
    @ResponseBody
    public Object selfLookData(HttpSession session){
        LOGGER.info("Enter selfLookData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        MemberTable loginUser = (MemberTable)session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        //int userId = loginUser.getId();
        String positionName = positionService.getBaseMapper().selectById(loginUser.getPosition_id()).getPosition_name();
        MemberVo3 selfInfo = new MemberVo3(loginUser, positionName);
        Map resMap = JSON.parseObject(JSON.toJSONString(selfInfo), Map.class);
        return LayUIResponseBo.ok(resMap);
    }

    @PostMapping(value = "/member/self/save")
    @ResponseBody
    public Object selfInfoSave(HttpSession session, @RequestBody Map reqm){
        LOGGER.info("Enter eventInfomation method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        MemberTable loginUser = (MemberTable)session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        MemberVo3 thedata = JSON.parseObject(JSON.toJSONString(reqm), MemberVo3.class);
        loginUser.setGender(thedata.getGender());
        loginUser.setUser_name(thedata.getUser_name());
        loginUser.setUser_number(thedata.getUser_number());
        loginUser.setClass_name(thedata.getClass_name());
        loginUser.setTelephone(thedata.getTelephone());
        loginUser.setPassword(thedata.getPassword());
        loginUser.setProfile(thedata.getProfile());
        userService.updateById(loginUser);
        return LayUIResponseBo.ok();
    }

}
