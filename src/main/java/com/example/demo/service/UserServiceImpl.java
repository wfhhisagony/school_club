package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.MemberTableDao;
import com.example.demo.dao.PositionTableDao;
import com.example.demo.entity.MemberTable;
import com.example.demo.entity.PositionTable;
import com.example.demo.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/11/30/20:31
 */
@Service
public class UserServiceImpl extends ServiceImpl<MemberTableDao, MemberTable> implements IUserService {

    @Autowired
    MemberTableDao memberTableDao;

    @Autowired
    PositionTableDao positionTableDao;

    public MemberTable queryUserByName(String username) {
        MemberTable member = memberTableDao.queryUserByName(username);
        return member;
    }

    public MemberTable queryUserByQQ(String qq) {
        MemberTable member = memberTableDao.queryUserByQQ(qq);
        return member;
    }

    public MemberVo getMemberVoByMemberId(int memberId){
        MemberTable member = memberTableDao.selectByPrimaryKey(memberId);
        PositionTable position = positionTableDao.selectByPrimaryKey(member.getPosition_id());
        MemberVo memberVo = new MemberVo();
        memberVo.setId(memberId);
        System.out.println("postion:");
        memberVo.setPosition_name(position.getPosition_name());
        memberVo.setGender(member.getGender()==0?"男":"女");
        memberVo.setUser_name(member.getUser_name());
        memberVo.setUser_number(member.getUser_number());
        memberVo.setClass_name(member.getClass_name());
        return memberVo;
    }

    @Override
    public List<MemberTable> getAllMemberList() {
        List<MemberTable> memberList = memberTableDao.getAllMemberList();
        return memberList;
    }


    @Override
    public List<MemberTable> getInClubMemberList() {
        return memberTableDao.getInClubMemberList();
    }

    @Override
    public List<MemberTable> getJoinClubMemberList() {
        return memberTableDao.getJoinClubMemberList();
    }

    @Override
    public List<MemberTable> getPastMemberList() {
        return memberTableDao.getPastMemberList();
    }

}
