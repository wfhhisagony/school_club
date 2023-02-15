package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/06/16:15
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class EventVo2 implements Serializable {
    private Integer event_id;

    private String place;

    // 只改了一下这里
    private String start_time;

    private String end_time;

    private String detail;

    private Byte finish_flag;

    private String checked_y;

    private String checked_n;

    private String title;

    private Byte type_flag;

    private Date update_time;

    private static final long serialVersionUID = 1L;
}
