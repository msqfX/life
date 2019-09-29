package com.dlion.life.base.api;

import com.dlion.life.base.entity.Water;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/23
 */
@FeignClient(value = "life-base")
public interface WaterApi {

    /**
     * 新增流水记录
     *
     * @param water
     * @return
     */
    @PostMapping("/life-base-water/add")
    void add(@RequestBody Water water);

    /**
     * 根据时间查询流水
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    @GetMapping("/life-base-water/listByTime")
    List<Water> listByTime(@RequestParam("userId") Integer userId, @RequestParam("startTime") Date startTime,
                           @RequestParam("endTime") Date endTime);

}
