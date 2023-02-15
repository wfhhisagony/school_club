package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.FundTable;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/02/22:51
 */
@Component
public interface IFundService extends IService<FundTable> {
    List<FundTable> getFundTableList();
}
