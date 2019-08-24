package com.dlion.life.base.api;

import com.dlion.life.base.entity.Water;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/8/23
 */
@FeignClient(name = "life-base-water", url = "http://localhost:8081")
public interface WaterApi {

    /**
     * 新增流水记录
     *
     * @param water
     * @return
     */
    @PostMapping
    void add(@RequestBody Water water);

    /**
     * 根据时间查询流水
     *
     * @param userId
     * @param startTime
     * @param endTime
     * @return
     */
    List<Water> listByTime(@RequestParam("userId") Integer userId, @RequestParam("startTime") Date startTime,
                           @RequestParam("endTime") Date endTime);

}
