package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.Data;

/**
 * position_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.PositionTable")
@Data
public class PositionTable implements Serializable {
    @TableId
    private Integer position_id;

    private String position_name;

    private String right_level;

    private static final long serialVersionUID = 1L;
}
