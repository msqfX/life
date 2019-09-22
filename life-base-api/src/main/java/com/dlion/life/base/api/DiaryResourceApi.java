package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.DiaryResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@FeignClient(name = "life-base-diaryResource", url = "localhost:8081")
public interface DiaryResourceApi {

    @GetMapping("/life-base-diaryResource/getById")
    DiaryResource getById(@RequestParam("id") Integer Id);

    @PostMapping("/life-base-diaryResource/add")
    void add(@RequestBody DiaryResource diaryResource);

    @DeleteMapping("/life-base-diaryResource/delete")
    void delete(@RequestParam("id") Integer id);

    @GetMapping("/life-base-diaryResource/getByDiaryId")
    List<DiaryResource> getByDiaryId(@RequestParam("diaryId") Integer diaryId);

}
