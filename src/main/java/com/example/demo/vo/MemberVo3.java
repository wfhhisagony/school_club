package com.example.demo.vo;

import com.example.demo.entity.MemberTable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/08/21:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo3 implements Serializable {

    private Byte gender;

    private String user_name;

    private String user_number;

    private String class_name;

    private int department_id;

    private String telephone;

    private String qq;

    private String password;

    private String profile;

    // 注册时间
    private Date creat_time;

    private String position_name;

    // 自定义的一个构造方法
    public MemberVo3(MemberTable memberTable, String position_name){
        this.gender = memberTable.getGender();
        this.user_name = memberTable.getUser_name();
        this.user_number = memberTable.getUser_number();
        this.class_name = memberTable.getClass_name();
        this.department_id = memberTable.getDepartment_id();
        this.telephone = memberTable.getTelephone();
        this.qq = memberTable.getQq();
        this.password = memberTable.getPassword();
        this.profile = memberTable.getProfile();
        this.creat_time = memberTable.getCreat_time();
        this.position_name = position_name;
    }

    // 自定义的一个构造方法
    public MemberVo3(MemberTable memberTable){
        this.gender = memberTable.getGender();
        this.user_name = memberTable.getUser_name();
        this.user_number = memberTable.getUser_number();
        this.class_name = memberTable.getClass_name();
        this.department_id = memberTable.getDepartment_id();
        this.telephone = memberTable.getTelephone();
        this.qq = memberTable.getQq();
        this.password = memberTable.getPassword();
        this.profile = memberTable.getProfile();
        this.creat_time = memberTable.getCreat_time();
    }

}
