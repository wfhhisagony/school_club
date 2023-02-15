package com.example.demo.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.common.RestResponseBo;
import com.example.demo.entity.MemberTable;
import com.example.demo.service.IUserService;
import com.example.demo.utils.WebConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 用户后台登录/登出
 * Created by BlueT on 2017/3/11.
 */
@Controller
@RequestMapping("/user")
public class AuthController extends BaseController{

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private IUserService userService;

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

    //@GetMapping(value = "/relogin")
    //public String redirectLogin(){
    //    return "redirect:/user/login";
    //}

    //@PostMapping(value = "login")
    //@ResponseBody  // @Controller注解中，返回json数据需要加上这个注解
    //public RestResponseBo doLogin(@RequestParam String username,
    //                              @RequestParam String password,
    //                              @RequestParam(required = false) String remember_me,
    //                              HttpSession session) {
    //    Subject subject = SecurityUtils.getSubject();
    //    UsernamePasswordToken token = new UsernamePasswordToken(username, password);    // 内部类，实现登录
    //
    //    try {
    //        // 使用shiro的login，不仅可以进行认证（登录），还可以进行授权；但这两个一般都需要重写它的方法，除非只使用它原封不动的内部类
    //        subject.login(token); // 执行登录方法，如果没有异常就说明OK了
    //        if (StringUtils.isNotBlank(remember_me)) {
    //            Subject currentSubject = SecurityUtils.getSubject();
    //            session.setAttribute(WebConst.LOGIN_SESSION_KEY, currentSubject.getSession().getAttribute(WebConst.LOGIN_SESSION_KEY));
    //            System.out.println("session:"+session.getAttribute(WebConst.LOGIN_SESSION_KEY));
    //        }
    //        return RestResponseBo.ok();
    //    } catch (UnknownAccountException accountException) {
    //        // 用户名不存在
    //        return RestResponseBo.fail("QQ错误");
    //    } catch (IncorrectCredentialsException e) {
    //        // 密码错误
    //        return RestResponseBo.fail("密码错误");
    //    }
    //}

    @PostMapping(value = "login")
    @ResponseBody
    public RestResponseBo doLogin(@RequestParam String username,
                                  @RequestParam String password,
                                  @RequestParam(required = false) String remember_me,
                                  HttpSession session) {
        Subject subject = SecurityUtils.getSubject();
        // shiro内部类，实现登录
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            // 使用shiro的login，不仅可以进行认证（登录），还可以进行授权
            subject.login(token); // 执行登录方法，如果没有异常就说明OK了
            if (StringUtils.isNotBlank(remember_me)) {
                Subject currentSubject = SecurityUtils.getSubject();
                session.setAttribute(WebConst.LOGIN_SESSION_KEY, currentSubject.getSession()
                        .getAttribute(WebConst.LOGIN_SESSION_KEY));
                System.out.println("session:"+session.getAttribute(WebConst.LOGIN_SESSION_KEY));
            }
            return RestResponseBo.ok();
        } catch (UnknownAccountException accountException) {
            // 用户名不存在
            return RestResponseBo.fail("QQ错误");
        } catch (IncorrectCredentialsException e) {
            // 密码错误
            return RestResponseBo.fail("密码错误");
        }
    }

    /**
     * 注销
     *
     * @param session
     * @param response
     */
    @PostMapping("/logout")
    @ResponseBody
    public RestResponseBo logout(HttpSession session, HttpServletResponse response, HttpServletRequest request) {
        LOGGER.info("Enter logout method");
        session.removeAttribute(WebConst.LOGIN_SESSION_KEY);
        return RestResponseBo.ok();
        //Cookie cookie = new Cookie(WebConst.USER_IN_COOKIE, "");
        //cookie.setValue(null);
        //cookie.setMaxAge(0);// 立即销毁cookie
        //cookie.setPath("/");
        //response.addCookie(cookie);
        //try {
        //    response.sendRedirect("/user/login");
        //} catch (IOException e) {
        //    e.printStackTrace();
        //    LOGGER.error("注销失败", e);
        //}
    }

}
