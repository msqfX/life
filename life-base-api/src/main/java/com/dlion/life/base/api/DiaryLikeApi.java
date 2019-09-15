package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryLike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@FeignClient(name = "life-base-diaryLink", url = "localhost:8081")
public interface DiaryLikeApi {

    @GetMapping("/life-base-diaryLink/getByDiaryIdAndUserId")
    DiaryLike getByDiaryIdAndUserId(@RequestParam("diaryId") Integer diaryId, @RequestParam("userId") Integer userId);

}
