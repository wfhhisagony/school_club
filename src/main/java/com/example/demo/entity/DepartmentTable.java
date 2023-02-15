package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * department_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.DepartmentTable")
@Data
public class DepartmentTable implements Serializable {
    @TableId
    private Integer department_id;

    private String department_name;

    private String department_brief;

    private static final long serialVersionUID = 1L;
}
