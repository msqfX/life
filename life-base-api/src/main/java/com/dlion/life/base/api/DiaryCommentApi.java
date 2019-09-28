package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(name = "life-base-diaryComment", url = "localhost:8081")
public interface DiaryCommentApi {

    @PostMapping("/life-base-diaryComment/add")
    void add(@RequestBody DiaryComment diaryComment);

    @GetMapping("/life-base-diaryComment/getByDiaryId")
    List<DiaryComment> getByDiaryId(@RequestParam("diaryId") Integer diaryId);

    /**
     * 根据被评论者的ID查询
     *
     * @param respondentId
     * @return
     */
    @GetMapping("/life-base-diaryComment/ListByRespondentId")
    List<DiaryComment> ListByRespondentId(@RequestParam("respondentId") Integer respondentId);
}
