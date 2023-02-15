package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * member_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.MemberTable")
@Data
public class MemberTable implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer position_id;

    private Byte gender;

    private String user_name;

    private String user_number;

    private String class_name;

    private Integer department_id;

    private String profile;

    private String telephone;

    private Byte in_club;

    @TableField(fill= FieldFill.INSERT)
    private Date creat_time;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private String qq;

    private String password;

    private static final long serialVersionUID = 1L;
}
