package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.EventTableDao;
import com.example.demo.dao.MemberTableDao;
import com.example.demo.dao.PositionTableDao;
import com.example.demo.entity.EventTable;
import com.example.demo.entity.PositionTable;
import com.example.demo.vo.EventVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/12/01/16:53
 */
@Service
public class EventService extends ServiceImpl<EventTableDao, EventTable> implements IEventService{

    @Autowired
    EventTableDao eventTableDao;

    @Autowired
    MemberTableDao memberTableDao;

    @Override
    public List<EventTable> getEventTableList() {
        List<EventTable> eventTableList = eventTableDao.getEventTableList();
        return eventTableList;
    }

    @Override
    public List<EventTable> getGoingEventTableList(){
        List<EventTable> eventTableList = eventTableDao.getGoingEventTableList();
        return eventTableList;
    }

    @Override
    public EventVo getEventVOByEventId(int event_id) {
        EventTable eventTable = eventTableDao.selectByPrimaryKey(event_id);
        EventVo eventVo = new EventVo();
        eventVo.setEvent_id(event_id);
        eventVo.setType_flag(eventTable.getType_flag());
        eventVo.setPlace(eventTable.getPlace());
        eventVo.setStart_end_time(eventTable.getStart_end_time());
        eventVo.setTitle(eventTable.getTitle());
        return eventVo;
    }

    @Override
    public boolean signup(int event_id, int user_id){
        EventTable eventTable = eventTableDao.selectById(event_id);
        String temp = eventTable.getChecked_n() == null ? "":eventTable.getChecked_n().trim();
        String no_checked = temp;
        String user_id_string = ((Integer)user_id).toString();
        //if(no_checked.isEmpty())
        // 需要new ArrayList否则不能使用add和remove方法
        List<String> no_checked_member_ids = new ArrayList<>(Arrays.asList(no_checked.split(";")));
        if(no_checked_member_ids.contains(user_id_string)) {         // 已经报名了
            return false;
        }
        StringBuffer new_no_checked = new StringBuffer(no_checked);
        new_no_checked.append(user_id_string+";");
        eventTable.setChecked_n(new_no_checked.toString());
        eventTableDao.updateById(eventTable);
        return true;
    }

    @Override
    public boolean getSigned(int event_id, int user_id) {
        EventTable eventTable = eventTableDao.selectById(event_id);
        String temp = eventTable.getChecked_y() == null ? "":eventTable.getChecked_y().trim();
        String yes_checked = temp;
        String user_id_string = ((Integer)user_id).toString();
        //if(no_checked.isEmpty())
        // 需要new ArrayList否则不能使用add和remove方法
        List<String> yes_checked_member_ids = new ArrayList<>(Arrays.asList(yes_checked.split(";")));
        if(yes_checked_member_ids.contains(user_id_string)) {         // 已经签到了
            return false;
        }
        StringBuffer new_yes_checked = new StringBuffer(yes_checked);
        new_yes_checked.append(user_id_string+";");
        eventTable.setChecked_y(new_yes_checked.toString());
        eventTableDao.updateById(eventTable);
        return true;
    }

    @Override
    public String removeUnSigned(int event_id, int user_id) {
        EventTable eventTable = eventTableDao.selectById(event_id);
        String temp = eventTable.getChecked_n() == null ? "":eventTable.getChecked_n().trim();
        String no_checked = temp;
        String user_id_string = ((Integer)user_id).toString();
        String res = "";
        List<String> no_checked_member_ids = new ArrayList<>(Arrays.asList(no_checked.split(";")));
        if(no_checked_member_ids.contains(user_id_string)) {         // 未报名
            res = user_id_string;
            no_checked_member_ids.remove(user_id_string);
            if(no_checked_member_ids.size() !=0) {
                eventTable.setChecked_n(String.join(";", no_checked_member_ids) + ";");
            }else{
                eventTable.setChecked_n("");
            }
            eventTableDao.updateById(eventTable);
        }
        return res;
    }

    @Override
    public boolean eventHasUser(int event_id, int user_id) {
        List<String> no_checked_member_ids = getUncheckedMemIds(event_id);
        String user_id_string = ((Integer)user_id).toString();
        if(no_checked_member_ids.contains(user_id_string)) {         // 未报名了
            return true;
        }
        return false;
    }

    @Override
    public List<EventTable> eventUncheckedList(int user_id) {
        List<EventTable> allEvents= eventTableDao.getEventTableList();
        List<EventTable> resEvents = new ArrayList();
        for(EventTable eventTable:allEvents){
            if(eventHasUser(eventTable.getEvent_id(), user_id)){
                resEvents.add(eventTable);
            }
        }
        return resEvents;
    }

    @Override
    public List<String> getUncheckedMemIds(int event_id) {
        EventTable eventTable = eventTableDao.selectById(event_id);
        String temp = eventTable.getChecked_n() == null ? "":eventTable.getChecked_n().trim();
        String no_checked = temp;
        List<String> no_checked_member_ids = new ArrayList<>(Arrays.asList(no_checked.split(";")));
        return no_checked_member_ids;
    }

    @Override
    public String getCheckedMemberNamesByIds(String checkedList){
        StringBuffer allName = new StringBuffer();
        List<String> no_checked_member_ids = new ArrayList<>(Arrays.asList(checkedList.split(";")));
        if(no_checked_member_ids.size()==1 && no_checked_member_ids.get(0)==""){
            return "";
        }
        for(String theId:no_checked_member_ids){
            int the_id = Integer.parseInt(theId);
            String theName = memberTableDao.selectById(the_id).getUser_name()+";";
            allName.append(theName);
        }
        return allName.toString();
    }

    @Override
    public List<EventTable> searchEventList(String keywords) {
        return eventTableDao.queryByKeyWords(keywords);
    }
}
