package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.PositionTable;

public interface PositionTableDao extends BaseMapper<PositionTable> {
    int deleteByPrimaryKey(Integer position_id);

    int insert(PositionTable record);

    int insertSelective(PositionTable record);

    PositionTable selectByPrimaryKey(Integer position_id);

    int updateByPrimaryKeySelective(PositionTable record);

    int updateByPrimaryKey(PositionTable record);
}
