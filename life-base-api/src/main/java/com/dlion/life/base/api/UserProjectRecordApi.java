package com.dlion.life.base.api;

import com.dlion.life.base.entity.UserProjectRecord;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@FeignClient(name = "life-base-userProjectRecord", url = "http://localhost:8081")
public interface UserProjectRecordApi {

    @GetMapping("/life-base-userProjectRecord/getByProjectId")
    List<UserProjectRecord> listByProjectId(@RequestParam("projectId") Integer projectId);

    @GetMapping("/life-base-userProjectRecord/getByUserId")
    UserProjectRecord getByUserId(@RequestParam("userId") Integer userId, @RequestParam("projectId") Integer projectId);

    @PostMapping("/life-base-userProjectRecord/add")
    void add(@RequestBody UserProjectRecord userProjectRecord);

}
