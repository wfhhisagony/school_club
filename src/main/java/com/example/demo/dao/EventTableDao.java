package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.EventTable;
import com.example.demo.entity.MemberTable;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

public interface EventTableDao extends BaseMapper<EventTable> {
    int deleteByPrimaryKey(Integer event_id);

    int insert(EventTable record);

    int insertSelective(EventTable record);

    EventTable selectByPrimaryKey(Integer event_id);

    int updateByPrimaryKeySelective(EventTable record);

    int updateByPrimaryKey(EventTable record);

    List<EventTable> getEventTableList();

    List<EventTable> getGoingEventTableList();

    /**
     * 使用关键字查找标题为其的
     * @return
     */
    List<EventTable> queryByKeyWords(String keyword);
}
