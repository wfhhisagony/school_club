package com.example.demo.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/05/21:25
 */

@NoArgsConstructor
@AllArgsConstructor
@Data
public class NoticeVo implements Serializable {
    private int id;
    private String detail;
    private String title;
}
