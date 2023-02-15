package com.example.demo.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.MemberTable;

import java.util.List;

public interface MemberTableDao extends BaseMapper<MemberTable> {
    int deleteByPrimaryKey(Integer id);

    int insert(MemberTable record);

    int insertSelective(MemberTable record);

    MemberTable selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(MemberTable record);

    int updateByPrimaryKey(MemberTable record);

    MemberTable queryUserByName(String name);

    MemberTable queryUserByQQ(String qq);

    List<MemberTable> getAllMemberList();

    List<MemberTable> getInClubMemberList();

    List<MemberTable> getJoinClubMemberList();

    List<MemberTable> getPastMemberList();


}
