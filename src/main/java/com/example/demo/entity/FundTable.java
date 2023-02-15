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
 * fund_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.FundTable")
@Data
public class FundTable implements Serializable {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Byte type;

    private String detail;

    private Integer amount;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private static final long serialVersionUID = 1L;
}
