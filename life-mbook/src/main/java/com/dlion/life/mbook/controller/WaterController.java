package com.dlion.life.mbook.controller;

import com.dlion.life.base.api.WaterApi;
import com.dlion.life.base.entity.Water;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.model.WaterModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 账户流水
 *
 * @author 李正元
 * @date 2019-08-04
 */
@RestController
@RequestMapping("/api/water")
public class WaterController {

    @Autowired
    private WaterApi waterApi;

    @GetMapping
    public Object getWater(@RequestParam String userId) {

        Map<String, Object> result = new HashMap<>();
        result.put("yearmonth", "2019年08月");
        result.put("sumin", new BigDecimal(0));
        result.put("sumout", new BigDecimal(0));
        result.put("jieyu", new BigDecimal(0));

        Map<String, Object> yusuan = new HashMap<>();

        yusuan.put("ishome", 1);
        yusuan.put("istag", 1);
        yusuan.put("baifenbi", "100.00");
        yusuan.put("jieyu", "1,500.00");

        List message = new ArrayList();

        result.put("yusuan", yusuan);

        result.put("message", message);

        return new ResponseModel(result);
    }

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
