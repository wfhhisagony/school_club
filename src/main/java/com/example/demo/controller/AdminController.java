package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.common.LayUIResponseBo;
import com.example.demo.common.RestResponseBo;
import com.example.demo.dao.*;
import com.example.demo.entity.*;
import com.example.demo.service.EventService;
import com.example.demo.service.FundService;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.utils.ContentVo;
import com.example.demo.utils.PositionConst;
import com.example.demo.utils.WebConst;
import com.example.demo.vo.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/11/30/22:29
 */
@Controller
@RequestMapping("/admin")
public class AdminController extends BaseController{
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private FundService fundService;

    @Autowired
    private JoinTableDao joinTableDao;

    @Autowired
    private NoticeTableDao noticeTableDao;

    @Autowired
    private PositionTableDao positionDao;

    @Autowired
    private DepartmentTableDao departmentTableDao;

    @Autowired
    private InfoTableDao infoTableDao;

    @GetMapping(value = "/member")
    public String memberLook(Model model, HttpSession session){
        System.out.println("getsession:"+session.getAttribute(WebConst.LOGIN_SESSION_KEY));
        MemberTable loginUser = (MemberTable) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        if(loginUser.getPosition_id()==9){ //普通成员无权进入
            return "redirect:/";
        }
        List<MemberVo> memberVos = new ArrayList();
        List<MemberTable> memberTableList = userService.getInClubMemberList();
        for (MemberTable memberTable : memberTableList){
            memberVos.add(new MemberVo(memberTable, positionDao.selectById(memberTable.getPosition_id()).getPosition_name()));
        }
        System.out.println(memberVos);
        model.addAttribute("members", memberVos);
        return "member_list";
    }

    @GetMapping(value="")
    public String redirectMemberLook(Model model, HttpSession session){
        return "redirect:/admin/member";  //这个不会包括Controller注解下面的
    }

    @GetMapping(value = "/member/detail/{id}")
    public String memberDetailLook(HttpSession session, @PathVariable String id, Model model){
        LOGGER.info("Enter memberDetailLook method"+id);
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){ //未登录
            return "redirect:/user/login";
        }
        int userId = Integer.parseInt(id);

        model.addAttribute("member_id", userId);
        System.out.println("当前model中member_id:"+model.getAttribute("member_id").toString());
        return "member_form";
    }

    @PostMapping(value = "member/detail/{id}")
    @ResponseBody
    public Object memberLookData(HttpSession session, @PathVariable String id){
        LOGGER.info("Enter memberLookData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        MemberTable theMem = userService.getById(Integer.parseInt(id));
        String positionName = positionDao.selectById(theMem.getPosition_id()).getPosition_name();
        MemberVo3 selfInfo = new MemberVo3(theMem, positionName);
        Map resMap = JSON.parseObject(JSON.toJSONString(selfInfo), Map.class);
        return LayUIResponseBo.ok(resMap);
    }


    @GetMapping("/event")
    public String eventLook(Model model){
        List<EventVo> eventVos = new ArrayList();
        List<EventTable> eventTableList = eventService.getEventTableList();
        for (EventTable eventTable : eventTableList){
            eventVos.add(eventService.getEventVOByEventId(eventTable.getEvent_id()));
        }
        System.out.println(eventVos);
        model.addAttribute("events", eventVos);
        return "event_list";
    }

    @GetMapping("/event/edit/{id}")
    public String eventEdit(Model model, @PathVariable String id){
        model.addAttribute("event_id", id);
        return "event_form";
    }

    @PostMapping(value = "event/edit/{id}")
    @ResponseBody
    public Object eventEditData(HttpSession session, @PathVariable String id){
        LOGGER.info("Enter eventEditData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        // event_id
        EventTable theEvent = eventService.getById(Integer.parseInt(id));
        Map resMap = JSON.parseObject(JSON.toJSONString(theEvent), Map.class);
        return LayUIResponseBo.ok(resMap);
    }

    @PostMapping(value = "event/editChecked")
    @ResponseBody
    public Object eventEditCheckedData(HttpSession session, @RequestBody Map reqm){
        LOGGER.info("Enter eventEditCheckedData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        System.out.println("Map"+reqm);
        //return LayUIResponseBo.ok();
        String checkedList = reqm.get("checkedList").toString();
        // event_id
        String checkedNames = eventService.getCheckedMemberNamesByIds(checkedList);
        Map<String, String> resMap = new HashMap<String, String>();
        resMap.put("checked", checkedNames);
        return LayUIResponseBo.ok(resMap);
    }

    @PostMapping(value = "event/save")
    @ResponseBody
    public Object eventSaveData(HttpSession session, @RequestBody EventTable eventTable){
        LOGGER.info("Enter eventSaveData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        System.out.println(eventTable);
        eventService.updateById(eventTable);
        return LayUIResponseBo.ok();
    }


    @GetMapping("/notice")
    public String noticeLook(Model model){
        // 每个页面的model不是共享的
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.orderByAsc("id");
        model.addAttribute("notices", noticeTableDao.selectList(wrapper));
        return "notice_list";
    }

    @GetMapping("/notice/edit/{id}")
    public String noticeEdit(Model model, @PathVariable String id){
        model.addAttribute("notice_id",id);
        return "notice_form";
    }

    @PostMapping(value = "notice/edit/{id}")
    @ResponseBody
    public Object noticeEditData(HttpSession session, @PathVariable String id){
        LOGGER.info("Enter noticeEditData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        NoticeTable thenotice = noticeTableDao.selectById(Integer.parseInt(id));
        Map resMap = JSON.parseObject(JSON.toJSONString(thenotice), Map.class);
        return LayUIResponseBo.ok(resMap);
    }

    @PostMapping(value = "notice/save")
    @ResponseBody
    public Object noticeSaveData(HttpSession session, @RequestBody NoticeTable noticeTable){
        LOGGER.info("Enter noticeSaveData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        System.out.println(noticeTable);
        noticeTableDao.updateById(noticeTable);
        //NoticeTable thenotice = noticeTableDao.selectById(Integer.parseInt(id));
        //Map resMap = JSON.parseObject(JSON.toJSONString(thenotice), Map.class);
        return LayUIResponseBo.ok();
    }

    @PostMapping(value="notice/delete/{id}")
    @ResponseBody
    public RestResponseBo noticeDelete(HttpSession session, @PathVariable String id){
        LOGGER.info("Enter noticeDelete method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)== null){
            return RestResponseBo.fail("未登录");
        }
        int result = noticeTableDao.deleteById(Integer.parseInt(id));
        if(result==1) {
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail("删除公告失败");
    }


    @GetMapping("/notice/add")
    public String noticeAdd(){
        return "notice_add";
    }

    @PostMapping(value="notice/add")
    @ResponseBody
    public RestResponseBo noticeAdding(NoticeTable notice, Model model){
        int result = noticeTableDao.insert(notice);
        if(result==1) {
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail();
    }

    @GetMapping("/join")
    public String joinLook(Model model){
        model.addAttribute("joins", userService.getJoinClubMemberList());
        return "join_list";
    }

    @PostMapping("/join")
    @ResponseBody
    public RestResponseBo ifAddJoinMember(@RequestParam(value = "id") int id, @RequestParam(value = "yn") boolean is_true){
        if(is_true){ //审核通过
            MemberTable theMember = userService.getById(id);
            theMember.setIn_club((byte) 1); //设置成员标志为1，就是使其为当前成员
            theMember.setCreat_time(new Date());  // 设置注册时间
            System.out.println(theMember.getCreat_time());
            userService.saveOrUpdate(theMember);
            return RestResponseBo.ok("审核通过");
        }else{ // 拒绝
            userService.getBaseMapper().deleteById(id); // 删除member_table中的该表项
            return RestResponseBo.ok("拒绝加入");
        }
    }

    @GetMapping("/join/detail/{id}")
    public String joinPersonLook(Model model, @PathVariable String id){
        model.addAttribute("join_id", id);
        return "join_form";
    }

    @PostMapping("/join/detail/{id}")
    @ResponseBody
    public Object joinPersonLookData(HttpSession session, Model model, @PathVariable String id){
        LOGGER.info("Enter joinPersonLookData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)== null){
            return LayUIResponseBo.fail();
        }
        // 这个getById也不用一定去转成int，String类型也能查
        MemberTable theMem = userService.getById(Integer.parseInt(id));
        MemberVo3 selfInfo = new MemberVo3(theMem);
        Map resMap = JSON.parseObject(JSON.toJSONString(selfInfo), Map.class);
        return LayUIResponseBo.ok(resMap);
    }

    @GetMapping("/fund")
    public String fundLook(Model model){
        model.addAttribute("funds",fundService.getFundTableList());
        return "fund_list";
    }

    @GetMapping("/fund/add")
    public String fundAdd(){
        return "fund_add";
    }

    @PostMapping(value="fund/add")
    @ResponseBody
    public RestResponseBo fundAdding(FundTable fundTable, Model model){
        LOGGER.info("Enter fundAdding method");
        System.out.println(fundTable);
        int result = fundService.getBaseMapper().insert(fundTable);
        if(result==1) {
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail();
    }

    @PostMapping(value="fund/delete")
    @ResponseBody
    public RestResponseBo fundDelete(@RequestParam String id){
        LOGGER.info("Enter fundDelete method"+id);
        int result = fundService.getBaseMapper().deleteById(Integer.parseInt(id));
        System.out.println("fund delete result:"+result);
        if(result==1){
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail();
    }

    @GetMapping("/fund/edit/{id}")
    public String fundEdit(Model model, @PathVariable String id){
        model.addAttribute("fund_id",id);
        return "fund_form";
    }

    @PostMapping(value = "fund/edit/{id}")
    @ResponseBody
    public Object fundEditData(HttpSession session, @PathVariable String id){
        LOGGER.info("Enter fundEditData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        FundTable theFund = fundService.getById(Integer.parseInt(id));
        Map resMap = JSON.parseObject(JSON.toJSONString(theFund), Map.class);
        return LayUIResponseBo.ok(resMap);
    }

    @PostMapping(value = "fund/save")
    @ResponseBody
    public Object fundSaveData(HttpSession session, @RequestBody FundTable fundTable){
        LOGGER.info("Enter fundSaveData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        System.out.println(fundTable);
        fundService.updateById(fundTable);
        return LayUIResponseBo.ok();
    }

    @GetMapping("/event/add")
    public String eventAdd(){
        return "event_add";
    }

    @PostMapping(value="event/add")
    @ResponseBody
    @Transactional  // 声明事务处理，加到Controller层中则采用手动回滚方法
    public RestResponseBo eventAdding(HttpSession session, EventVo2 eventVo, Model model){
        LOGGER.info("Enter eventAdding method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return RestResponseBo.fail("未登录");
        }
        MemberTable loginUser = (MemberTable) session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        LOGGER.info(loginUser.toString());
        if(eventVo.getType_flag()==1 && loginUser.getPosition_id()!=1 && loginUser.getPosition_id()!=2){
            // 只有会长才能发布会议
            return RestResponseBo.fail("权限不够");
        }
        EventTable event = new EventTable(eventVo);
        System.out.println("event before insert:"+event);
        try {
            int result = eventService.getBaseMapper().insert(event); // 方法执行完毕才会insert
            if(result!=1) {
                return RestResponseBo.fail();
            }
            System.out.println("event after insert:"+event);
            if (eventVo.getType_flag() == 1) {  // 会议则在会成员都未签到，除了会长和组织部部长
                List<MemberTable> memberTableList = userService.getInClubMemberList();
                int[] excludePosIds = {1,2,3,4}; // 除了会长和组织部部长
                StringBuffer addCheckedIds = new StringBuffer("");
                StringBuffer addUnCheckedIds = new StringBuffer("");
                for (int i = 0; i < memberTableList.size(); i++) {
                    MemberTable theMember = memberTableList.get(i);
                    int user_id = theMember.getId();
                    if (Arrays.binarySearch(excludePosIds, theMember.getPosition_id())>=0) {
                        addCheckedIds.append(user_id+";");
                        //eventService.getSigned(event.getEvent_id(), user_id);
                    } else {
                        addUnCheckedIds.append(user_id+";");
                        //eventService.signup(event.getEvent_id(), user_id);
                    }
                }
                event.setChecked_n(addUnCheckedIds.toString());
                event.setChecked_y(addCheckedIds.toString());
                eventService.updateById(event);
            }
        }
        catch(Exception e){
            LOGGER.error(e.toString());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly(); //手动回滚
            return RestResponseBo.fail(e.toString());
        }
        return RestResponseBo.ok();
    }

    @GetMapping(value = "event/checkbook/{event_id}")
    public String eventCheckBook(@PathVariable int event_id, Model model){
        LOGGER.info("Enter eventCheckBook method:"+ event_id);
        model.addAttribute("event_id", event_id);
        return "event_signup_members";
    }


    @PostMapping(value = "event/checkbook")
    @ResponseBody
    public Object eventCheckBookData(HttpSession session, @RequestBody Map map){
        LOGGER.info("Enter eventCheckBookData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail("未登录");
        }
        System.out.println(map);
        List<String> unchecked_memIds = eventService.getUncheckedMemIds(Integer.parseInt(map.get("event_id").toString()));
        List<MemberVo2> memberVo2 = new ArrayList();
        if(isStringListBlank(unchecked_memIds)){ //没有成员
            return LayUIResponseBo.ok();
        }
        for(String sid:unchecked_memIds){
            int userId = Integer.parseInt(sid);
            MemberTable theMM = userService.getById(userId);
            String position_name = positionDao.selectById(theMM.getPosition_id()).getPosition_name();
            String department_name = departmentTableDao.selectById(theMM.getDepartment_id()).getDepartment_name();
            MemberVo2 theM = new MemberVo2(theMM,position_name, department_name);
            memberVo2.add(theM);
        }
        List<Map> resMap = JSON.parseArray(JSON.toJSONString(memberVo2), Map.class);
        System.out.println("resMap"+resMap);
        return LayUIResponseBo.ok(resMap);
    }

    public boolean isStringListBlank(List<String> src_list){
        if(src_list.size() == 0)
            return true;
        if(src_list.size()==1){
            if(src_list.get(0).isEmpty()) {
                return true;
            }
        }
        return false;
    }

    @PostMapping(value = "event/checkMember")
    @ResponseBody
    public Object eventCheckMember(HttpSession session, @RequestBody Map map){
        LOGGER.info("Enter eventCheckMember method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail("未登录");
        }
        System.out.println(map);
        int eventId = Integer.parseInt(map.get("event_id").toString());
        int userId = Integer.parseInt(map.get("user_id").toString());
        eventService.removeUnSigned(eventId, userId);
        eventService.getSigned(eventId, userId);
        return LayUIResponseBo.ok("签到成功");
    }

    @PostMapping(value = "event/delete/{id}")
    @ResponseBody
    public RestResponseBo deleteEvent(@PathVariable String id, HttpServletRequest request) {
        LOGGER.info("Enter deleteEvent method"+id);
        int result = eventService.getBaseMapper().deleteById(Integer.parseInt(id));
        System.out.println("event delete result:"+result);
        if(result==1){
            return RestResponseBo.ok();
        }
        return RestResponseBo.fail();
    }

    /**
     * 职位转让
     * 交换成员的department_id、position_id
     * 会长 <==> 全员  ;   部长 <==> 自己部门成员
     * @param session
     * @param id
     * @return
     */
    @PostMapping(value= "position/transfer")
    @ResponseBody
    public RestResponseBo transferPosition(HttpSession session, @RequestParam String id){
        LOGGER.info("Enter transferPosition method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return RestResponseBo.fail();
        }
        MemberTable loginUser = (MemberTable)session.getAttribute(WebConst.LOGIN_SESSION_KEY);
        int user_posId = loginUser.getPosition_id();
        int user_depId = loginUser.getDepartment_id();
        if(user_posId == 9){    //普通成员
            return RestResponseBo.fail("权限不够");
        }
        MemberTable theMem = userService.getById(id);
        int theMem_posId = theMem.getPosition_id();
        int theMem_depId = theMem.getDepartment_id();
        if(user_posId == 1 || user_posId == 2){ //会长和副会长
            loginUser.setPosition_id(theMem_posId);
            loginUser.setDepartment_id(theMem_depId);
            theMem.setPosition_id(user_posId);
            theMem.setDepartment_id(user_depId);
            session.setAttribute(WebConst.LOGIN_SESSION_KEY, loginUser);
            userService.saveOrUpdate(loginUser);
            userService.saveOrUpdate(theMem);
            System.out.println("转让后变为"+session.getAttribute(WebConst.LOGIN_SESSION_KEY).toString());
            return RestResponseBo.ok();
        }else{
            if(user_depId != theMem_depId){
                return RestResponseBo.fail("权限不够");
            }else{
                loginUser.setPosition_id(theMem_posId);
                loginUser.setDepartment_id(theMem_depId);
                theMem.setPosition_id(user_posId);
                theMem.setDepartment_id(user_depId);
                session.setAttribute(WebConst.LOGIN_SESSION_KEY, loginUser);
                userService.saveOrUpdate(loginUser);
                userService.saveOrUpdate(theMem);
                System.out.println("转让后变为"+session.getAttribute(WebConst.LOGIN_SESSION_KEY).toString());
                return RestResponseBo.ok();
            }
        }
    }

    //public void exchangePos(MemberTable s, MemberTable t, HttpSession session){
    //    int user_posId = s.getPosition_id();
    //    int user_depId = s.getDepartment_id();
    //    int theMem_posId = t.getPosition_id();
    //    int theMem_depId = t.getDepartment_id();
    //
    //}

    @PostMapping(value= "position/elevate")
    @ResponseBody
    public RestResponseBo elevatePosition(HttpSession session, @RequestParam Map reqm){
        LOGGER.info("Enter elevatePosition method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return RestResponseBo.fail("未登录");
        }
        System.out.println("reqm:"+reqm);
        int user_id = Integer.parseInt(reqm.get("user_id").toString());
        MemberTable theUser = userService.getById(user_id);
        int orig_pos_id = theUser.getPosition_id();
        int pos_id = Integer.parseInt(reqm.get("pos_id").toString());
        if(orig_pos_id == 1 || orig_pos_id == 2){ //会长或副会长
            return RestResponseBo.fail("权限不够");
        }
        theUser.setPosition_id(pos_id);
        theUser.setDepartment_id(PositionConst.PosId2DepId.get(pos_id));
        userService.updateById(theUser);
        return RestResponseBo.ok();
    }


    /**
     * 会长撤销部长的职位
     * 部长变为该部门的普通成员, 其实只要更改一下成员的职位id(position_id)即可
     * 规定一下，不能撤职副会长
     * @param session
     * @param reqm
     *   user_id
     * @return
     */
    @PostMapping(value= "position/dismiss")
    @ResponseBody
    public RestResponseBo dismissPosition(HttpSession session, @RequestParam Map reqm){
        LOGGER.info("Enter dismissPosition method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return RestResponseBo.fail("未登录");
        }
        System.out.println("reqm:"+reqm);
        int user_id = Integer.parseInt(reqm.get("user_id").toString());
        MemberTable theUser = userService.getById(user_id);
        int orig_pos_id = theUser.getPosition_id();
        if(orig_pos_id == 1 || orig_pos_id == 2){ //会长或副会长
            return RestResponseBo.fail("权限不够");
        }
        theUser.setPosition_id(9);
        userService.updateById(theUser);
        return RestResponseBo.ok();
    }


    @GetMapping("/info")
    public String infoLook(Model model){
        model.addAttribute("infos",infoTableDao.selectList(null));
        return "info_list";
    }

    @GetMapping("/info/edit/{id}")
    public String infoEdit(Model model, @PathVariable String id){
        model.addAttribute("info_id",id);
        return "info_form";
    }

    @PostMapping(value = "info/edit/{info_id}")
    @ResponseBody
    public Object infoEditData(HttpSession session, @PathVariable String info_id){
        LOGGER.info("Enter infoEditData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY) == null){
            return LayUIResponseBo.fail();
        }
        InfoTable theInfo = infoTableDao.selectById(Integer.parseInt(info_id));
        Map resMap = JSON.parseObject(JSON.toJSONString(theInfo), Map.class);
        return LayUIResponseBo.ok(resMap);
    }



    @PostMapping(value = "info/save")
    @ResponseBody
    public Object infoSaveData(HttpSession session, @RequestBody InfoTable infoTable){
        LOGGER.info("Enter infoSaveData method");
        if(session.getAttribute(WebConst.LOGIN_SESSION_KEY)==null){
            return LayUIResponseBo.fail();
        }
        System.out.println(infoTable);
        infoTableDao.updateById(infoTable);
        return LayUIResponseBo.ok();
    }



}
