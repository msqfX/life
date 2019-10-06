package com.dlion.life.base.api;

import com.dlion.life.base.entity.DiaryComment;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(value = "life-base")
public interface DiaryCommentApi {

    @PostMapping("/life-base-diaryComment/add")
    Integer add(@RequestBody DiaryComment diaryComment);

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

    @GetMapping("/life-base-diaryComment/getById")
    DiaryComment getById(@RequestParam("id") Integer id);

    @DeleteMapping("/life-base-diaryComment/delete")
    void delete(@RequestParam("id") Integer id);

    @GetMapping("/life-base-diaryComment/listByPid")
    List<DiaryComment> listByPid(@RequestParam("diaryId") Integer diaryId, @RequestParam("id") Integer id);
}
