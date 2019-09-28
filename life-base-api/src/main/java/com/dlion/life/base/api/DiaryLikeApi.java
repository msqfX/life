package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryLike;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@FeignClient(name = "life-base-diaryLike", url = "localhost:8081")
public interface DiaryLikeApi {

    @GetMapping("/life-base-diaryLike/getByDiaryIdAndUserId")
    DiaryLike getByDiaryIdAndUserId(@RequestParam("diaryId") Integer diaryId, @RequestParam("userId") Integer userId);

    @GetMapping("/life-base-diaryLike/listByDiaryId")
    List<DiaryLike> listByDiaryId(@RequestParam("diaryId") Integer diaryId);

    @PostMapping("/life-base-diaryLike/like")
    Integer like(@RequestBody DiaryLike diaryLike);

    @DeleteMapping("/life-base-diaryLike/delete")
    void delete(@RequestParam("likeRecordId") Integer likeRecordId);

    @GetMapping("/life-base-diaryLike/listByAdmirerId")
    List<DiaryLike> listByAdmirerId(@RequestParam("admirerId") Integer admirerId);
}
