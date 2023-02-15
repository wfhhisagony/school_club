package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.FundTable;

import java.util.List;

public interface FundTableDao extends BaseMapper<FundTable> {
    int deleteByPrimaryKey(Integer id);

    int insert(FundTable record);

    int insertSelective(FundTable record);

    FundTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(FundTable record);

    int updateByPrimaryKey(FundTable record);

    List<FundTable> getAllFund();
}
