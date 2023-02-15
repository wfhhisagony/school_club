package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo.entity.EventTable;
import com.example.demo.entity.MemberTable;
import com.example.demo.entity.PositionTable;
import com.example.demo.vo.EventVo;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/01/16:52
 */
@Component
public interface IEventService extends IService<EventTable>{
    public List<EventTable> getEventTableList();

    public List<EventTable> getGoingEventTableList();

    public EventVo getEventVOByEventId(int event_id);

    /**
     * 报名
     * @param event_id
     * @param user_id
     * @return
     */
    public boolean signup(int event_id, int user_id);

    /**
     * 签到
     * 只添加签到名单，不删除未签到名单
     */
    public boolean getSigned(int event_id, int user_id);

    /**
     * 从未签到表中移除
     * 返回删除的id
     */
    public String removeUnSigned(int event_id, int user_id);

    public boolean eventHasUser(int event_id, int user_id);

    public List<EventTable> eventUncheckedList(int user_id);

    public List<String> getUncheckedMemIds(int event_id);

    public String getCheckedMemberNamesByIds(String checkedList);

    public List<EventTable> searchEventList(String keywords);
}
