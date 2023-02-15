package com.example.demo.service;



import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.FundTableDao;
import com.example.demo.entity.FundTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/02/22:51
 */
@Service
public class FundService extends ServiceImpl<FundTableDao, FundTable> implements IFundService{
    @Autowired
    FundTableDao fundTableDao;

    @Override
    public List<FundTable> getFundTableList(){
        return fundTableDao.getAllFund();
    }
}
