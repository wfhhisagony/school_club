package com.example.demo.controller;


import com.example.demo.entity.MemberTable;
import com.example.demo.utils.TaleUtils;
import org.apache.shiro.cache.MapCache;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by 13 on 2017/2/21.
 */
public abstract class BaseController {

    public static String THEME = "themes/default";

    //protected MapCache cache = MapCache.single();

    /**
     * 主页的页面主题
     * @param viewName
     * @return
     */
    public String render(String viewName) {
        return THEME + "/" + viewName;
    }

    public BaseController title(HttpServletRequest request, String title) {
        request.setAttribute("title", title);
        return this;
    }

    public BaseController keywords(HttpServletRequest request, String keywords) {
        request.setAttribute("keywords", keywords);
        return this;
    }

    /**
     * 获取请求绑定的登录对象
     * @param request
     * @return
     */
    public MemberTable user(HttpServletRequest request) {
        return TaleUtils.getLoginUser(request);
    }

    //public Integer getUid(HttpServletRequest request){
    //    return this.user(request).getUid();
    //}

    public String render_404() {
        return "comm/error_404";
    }

}
