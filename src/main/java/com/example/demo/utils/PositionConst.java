package com.example.demo.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class PositionConst {

    // java8新特性
    public static Map<String, Integer> nameToId = new HashMap<String, Integer>(){{
        put("会长",1);
        put("副会长",2);
        put("组织部部长",3);
        put("组织部副部长",4);
        put("宣传部部长",5);
        put("宣传部副部长",6);
        put("外联部部长",7);
        put("外联部副部长",8);
        put("普通成员",9);
    }};

    public static Map<Integer, Integer> PosId2DepId = new HashMap<Integer, Integer>(){{
        put(1,1);
        put(2,1);
        put(3,2);
        put(4,2);
        put(5,3);
        put(6,3);
        put(7,4);
        put(8,4);
    }};

    public static Map<Integer, String> DepId2DepName = new HashMap<Integer, String>(){{
        put(1,"所有部");
        put(2, "组织部");
        put(3,"宣传部");
        put(4,"外联部");
    }};
}
