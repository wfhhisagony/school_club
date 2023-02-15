package com.example.demo.vo;

import com.example.demo.entity.MemberTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/07/23:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo2 implements Serializable {
    private int id;

    private String position_name;

    private String gender;

    private String user_name;

    private String user_number;

    private String class_name;

    private String department_name;


    // 自定义的一个构造方法
    public MemberVo2(MemberTable memberTable, String position_name, String department_name){
        this.id = memberTable.getId();
        this.position_name = position_name;
        this.gender = memberTable.getGender() == 0? "男":"女";
        this.user_name = memberTable.getUser_name();
        this.user_number = memberTable.getUser_number();
        this.class_name = memberTable.getClass_name();
        this.department_name = department_name;
    }

}
