package com.example.demo.vo;

import com.example.demo.entity.MemberTable;
import com.example.demo.utils.PositionConst;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/01/16:16
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVo implements Serializable {
    private int id;

    private String position_name;

    private String gender;

    private String user_name;

    private String user_number;

    private String class_name;

    private int in_club;

    private String department_name;

    // 自定义的一个构造方法
    public MemberVo(MemberTable memberTable, String position_name){
        this.id = memberTable.getId();
        this.position_name = position_name;
        this.gender = memberTable.getGender() == 0? "男":"女";
        this.user_name = memberTable.getUser_name();
        this.user_number = memberTable.getUser_number();
        this.class_name = memberTable.getClass_name();
        this.in_club = memberTable.getIn_club();
        this.department_name = PositionConst.DepId2DepName.get(memberTable.getDepartment_id());
    }

}
