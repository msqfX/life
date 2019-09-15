package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.DiaryResource;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@FeignClient(name = "life-base-diaryResource", url = "localhost:8081")
public interface DiaryResourceApi {

    @GetMapping("/life-base-diaryResource/getById")
    DiaryResource getById(@RequestParam("id") Integer Id);

    @GetMapping("/life-base-diaryResource/add")
    void add(@RequestBody DiaryResource diaryResource);

    @DeleteMapping("/life-base-diaryResource/delete")
    void delete(@RequestParam("id") Integer id);

    @GetMapping("/life-base-diaryResource/getByDiaryId")
    List<DiaryResource> getByDiaryId(@RequestParam("diaryId") Integer diaryId);

}
