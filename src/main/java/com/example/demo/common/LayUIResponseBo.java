package com.example.demo.common;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * 封装返回给layui页面的json(map)数据
 * @author lqh
 * @date 2021/12/05/9:21
 */
public class LayUIResponseBo {
    public static Object ok(){
        Map<String,Object> obj = new HashMap<>();
        obj.put("code",0);
        obj.put("success",true);
        obj.put("msg","成功");
        return obj;
    }

    public static Object ok(Object data){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("code",0);
        obj.put("success",true);
        obj.put("data",data);
        return obj;
    }

    public static Object ok(String msg,Object data){
        Map<String,Object> obj = new HashMap<String,Object>();
        obj.put("code",0);
        obj.put("success",true);
        obj.put("msg",msg);
        obj.put("data",data);
        return obj;
    }

    public static Object fail(){
        Map<String,Object> obj = new HashMap<>();
        obj.put("code",1);
        obj.put("success",false);
        obj.put("msg","请求失败");
        return obj;
    }

    public static Object fail(int code){
        Map<String,Object> obj = new HashMap<>();
        obj.put("code",code);
        obj.put("success",false);
        obj.put("msg","请求失败");
        return obj;
    }

    public static Object fail(String msg){
        Map<String,Object> obj = new HashMap<>();
        obj.put("code",1);
        obj.put("success",false);
        obj.put("msg",msg);
        return obj;
    }

    public static Object fail(int code, String msg) {
        Map<String, Object> obj = new HashMap<String, Object>();
        obj.put("code", code);
        obj.put("success",false);
        obj.put("msg", msg);
        return obj;
    }

}
