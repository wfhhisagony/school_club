package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/01/16:50
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventVo implements Serializable {
    private int event_id;

    private int type_flag;

    private String place;

    private String start_end_time;

    private String title;
}
