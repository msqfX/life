package com.dlion.life.base.api;

import com.dlion.life.base.entity.PersonalPageVisitRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/28
 */
@FeignClient(value = "life-base")
public interface PersonalPageVisitRecordApi {

    @GetMapping("/life-base-PersonalPageVisitRecord/getByUserId")
    List<PersonalPageVisitRecord> getByUserId(@RequestParam("visitedId") Integer visitedId);

}
