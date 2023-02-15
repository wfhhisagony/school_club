package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * info_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.InfoTable")
@Data
public class InfoTable implements Serializable {
    @TableId
    private Integer id;

    private String info_name;

    private String detail;

    @TableField(fill = FieldFill.UPDATE)
    private Date update_time;

    private static final long serialVersionUID = 1L;
}
