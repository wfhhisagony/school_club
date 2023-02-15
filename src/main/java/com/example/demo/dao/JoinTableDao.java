package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.JoinTable;

public interface JoinTableDao extends BaseMapper<JoinTable> {
    int deleteByPrimaryKey(Integer id);

    int insert(JoinTable record);

    int insertSelective(JoinTable record);

    JoinTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(JoinTable record);

    int updateByPrimaryKey(JoinTable record);
}
