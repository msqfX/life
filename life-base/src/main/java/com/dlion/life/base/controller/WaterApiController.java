package com.dlion.life.base.controller;

import com.dlion.life.base.api.WaterApi;
import com.dlion.life.base.entity.Water;
import com.dlion.life.base.service.WaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/23
 */
@RestController
public class WaterApiController implements WaterApi {

    @Autowired
    private WaterService waterService;

    @Override
    public void add(@RequestBody Water water) {

        waterService.add(water);
    }

    @Override
    public List<Water> listByTime(Integer userId, Date startTime, Date endTime) {

        return waterService.listByTime(userId, startTime, endTime);
    }
}
