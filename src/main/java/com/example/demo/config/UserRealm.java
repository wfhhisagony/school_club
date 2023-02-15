package com.example.demo.config;

import com.example.demo.entity.MemberTable;
import com.example.demo.entity.PositionTable;
import com.example.demo.service.PositionServiceImpl;
import com.example.demo.service.UserServiceImpl;
import com.example.demo.utils.WebConst;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *  用来认证和授权的realm
 */
//自定义的 UserRealm
public class UserRealm extends AuthorizingRealm {

    @Autowired
    UserServiceImpl userService;

    @Autowired
    PositionServiceImpl positionService;

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("用户密码认证成功,开始授权 执行 => doGetAuthorizationInfo");

        // 通过这个info来添加权限
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        // 拿到当前登录的这个对象, 这个getSubject是个静态方法，意思是这个类是个相当于缓存的东西
        // public static Subject getSubject()
        Subject subject = SecurityUtils.getSubject();
        // 拿到User对象，principal在执行认证的时候被封装进去了
        MemberTable currentUser = (MemberTable) subject.getPrincipal();
        // 设置当前用户的权限
        System.out.println(currentUser.getUser_name() + "的权限为 " + currentUser.getPosition_id());
        PositionTable p = positionService.getById(currentUser.getPosition_id());
        info.addStringPermission(p.getRight_level());

        return info;    //SimpleAuthorizationInfo
    }

    ////认证
    //@Override
    //protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
    //    System.out.println("执行了 => 认证AuthenticationToken");
    //    // 解开封装的token  调用login方法传入的token
    //    UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
    //    // 连接真实的数据库,先进行用户名认证
    //    MemberTable user = userService.queryUserByQQ(userToken.getUsername());
    //    if(user == null){
    //        //没有这个人
    //        return null; //抛出异常 UnknownAccountException
    //    }
    //    if(user.getIn_club() == 2){
    //        // 还未审核通过
    //        return null;
    //    }
    //    // 登录成功 将用户信息存入session
    //    Subject currentSubject = SecurityUtils.getSubject();
    //    Session session = currentSubject.getSession();
    //    // 往返回给服务器的session里放东西
    //    // th:if="${session.loginUser == null}" 可以取到值
    //    session.setAttribute(WebConst.LOGIN_SESSION_KEY,user);
    //
    //    // 密码认证，shiro做,通过实例化的userToken做
    //    // ""将默认getName()
    //    // getName返回的是在realm缓存中的其中一个名字而已，this.name = getClass().getName() + "_" + INSTANCE_COUNT.getAndIncrement();
    //    // 说不定有效参数就是user.getPassword()，这个要和subject.login传入的第二个参数一致才会返回真
    //    return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    //}

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        System.out.println("执行了 => 认证AuthenticationToken");
        // 解开封装的token  调用login方法传入的token
        UsernamePasswordToken userToken = (UsernamePasswordToken) authenticationToken;
        // 连接真实的数据库,先进行用户名认证
        MemberTable user = userService.queryUserByQQ(userToken.getUsername());
        if(user == null){
            //没有这个人
            return null; //抛出异常 UnknownAccountException
        }
        if(user.getIn_club() == 2){
            // 还未审核通过
            return null;
        }
        // 登录成功 将用户信息存入session
        Subject currentSubject = SecurityUtils.getSubject();
        Session session = currentSubject.getSession();
        // session暂存
        session.setAttribute(WebConst.LOGIN_SESSION_KEY,user);

        // 密码认证，shiro做,通过实例化的userToken做
        return new SimpleAuthenticationInfo(user,user.getPassword(),getName());
    }
}
