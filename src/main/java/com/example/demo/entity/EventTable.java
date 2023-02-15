package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.example.demo.vo.EventVo;
import com.example.demo.vo.EventVo2;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * event_table
 * @author
 */
@ApiModel(value="com.example.demo.entity.EventTable")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class EventTable implements Serializable {
    // 使用自增型id
    @TableId(value = "event_id", type = IdType.AUTO)
    private Integer event_id;

    private String place;

    private String start_end_time;

    private String detail;

    private Byte finish_flag;

    private String checked_y;

    private String checked_n;

    /**
     * 事件名
     */
    @ApiModelProperty(value="事件名")
    private String title;

    /**
     * 0 活动  1 会议
     */
    @ApiModelProperty(value="0 活动  1 会议")
    private Byte type_flag;

    // 通过mabatis-plus config去自动添加
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Date update_time;

    private static final long serialVersionUID = 1L;

    // 如果未定义隐式的构造器，会将其覆盖掉
    public EventTable(EventVo2 eventVo){
        this.type_flag = eventVo.getType_flag();
        this.title = eventVo.getTitle();
        this.place = eventVo.getPlace();
        this.start_end_time = eventVo.getStart_time()
                .replace('-','/') + "-"
                + eventVo.getEnd_time().replace('-', '/');
        this.detail = eventVo.getDetail();
        this.finish_flag = 0; //0 进行时， 1结束
    }
}
