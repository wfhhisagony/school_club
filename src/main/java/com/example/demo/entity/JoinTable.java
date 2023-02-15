package com.example.demo.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * join_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.JoinTable")
@Data
public class JoinTable implements Serializable {
    private Integer id;

    private String profile;

    private String class_name;

    private String name;

    private String number;

    private String telephone;

    private Integer department_id;

    private String qq;

    private String password;

    private static final long serialVersionUID = 1L;
}
