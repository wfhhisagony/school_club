package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.InfoTable;

public interface InfoTableDao extends BaseMapper<InfoTable> {
    int deleteByPrimaryKey(Integer id);

    int insert(InfoTable record);

    int insertSelective(InfoTable record);

    InfoTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(InfoTable record);

    int updateByPrimaryKey(InfoTable record);

    String queryByInfoName(String info_name);
}
