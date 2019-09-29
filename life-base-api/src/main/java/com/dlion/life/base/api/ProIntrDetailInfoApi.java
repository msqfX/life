package com.dlion.life.base.api;

import com.dlion.life.base.entity.ProIntrDetailInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/14
 */
@FeignClient(value = "life-base")
public interface ProIntrDetailInfoApi {

    @GetMapping("/life-base-proIntrDetailInfo/getByProjectId")
    List<ProIntrDetailInfo> getByProjectId(@RequestParam Integer projectId);

    @PostMapping("/life-base-proIntrDetailInfo/add")
    void add(@RequestBody ProIntrDetailInfo proIntrDetailInfo);

    @PutMapping("/life-base-proIntrDetailInfo/update")
    void update(@RequestBody ProIntrDetailInfo proIntrDetailInfo);

    @DeleteMapping("/life-base-proIntrDetailInfo/delete")
    void delete(@RequestParam("id") Integer id);

}
