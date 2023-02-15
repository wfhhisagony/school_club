package com.example.demo.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo.dao.PositionTableDao;
import com.example.demo.entity.PositionTable;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA
 *
 * @author lqh
 * @date 2021/11/30/20:41
 */
@Service
public class PositionServiceImpl extends ServiceImpl<PositionTableDao, PositionTable> implements IPositionService {

}
