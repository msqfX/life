package com.dlion.life.base.service;

import com.dlion.life.base.dao.WaterMapper;
import com.dlion.life.base.entity.Water;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/23
 */
@Service
public class WaterService {

    @Autowired
    private WaterMapper waterMapper;

    public void add(Water water) {

        waterMapper.insertSelective(water);
    }

    public List<Water> listByTime(Integer userId, Date startTime, Date endTime) {

        List<Water> waterList = waterMapper.selectByTime(userId, startTime, endTime);

        return waterList;
    }
}
