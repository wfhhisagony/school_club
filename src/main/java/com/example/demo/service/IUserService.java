package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.MemberTable;
import com.example.demo.vo.MemberVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/11/30/20:26
 */
@Component
public interface IUserService extends IService<MemberTable> {
    public MemberTable queryUserByName(String name);
    public MemberTable queryUserByQQ(String qq);
    public MemberVo getMemberVoByMemberId(int memberId);
    public List<MemberTable> getAllMemberList();

    /**
     * 当前成员列表
     * @return
     */
    public List<MemberTable> getInClubMemberList();

    /**
     * 申请加入人员列表
     * @return
     */
    public List<MemberTable> getJoinClubMemberList();

    public List<MemberTable> getPastMemberList();
}
