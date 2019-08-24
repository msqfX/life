package com.dlion.life.mbook.controller;

import com.dlion.life.base.api.WaterApi;
import com.dlion.life.base.entity.Water;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.model.WaterModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 账户流水
 *
 * @author 李正元
 * @date 2019/8/23
 */
@RestController
@RequestMapping("/api/water")
public class WaterController {

    @Autowired
    private WaterApi waterApi;

    /**
     * 新增流水记录
     *
     * @param waterModel
     * @return
     */
    @PostMapping
    public Object add(@RequestBody WaterModel waterModel) {

        Water water = new Water();

        BeanUtils.copyProperties(waterModel, water);

        waterApi.add(water);

        return new ResponseModel();
    }

    /**
     * 根据时间查询流水
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/list_time")
    public Object listByTime(@RequestParam("userId") Integer userId, @RequestParam("startTime") Date startTime,
                             @RequestParam("endTime") Date endTime) {

        List<Water> waterList = waterApi.listByTime(userId, startTime, endTime);

        List<WaterModel> modelList = waterList.stream().map(water -> {

            WaterModel model = new WaterModel();

            BeanUtils.copyProperties(water, model);

            return model;

        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }


}
