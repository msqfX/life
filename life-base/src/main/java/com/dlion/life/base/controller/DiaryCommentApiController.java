package com.dlion.life.base.controller;

import com.dlion.life.base.api.DiaryCommentApi;
import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.base.service.DiaryCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
public class DiaryCommentApiController implements DiaryCommentApi {

    @Autowired
    private DiaryCommentService diaryCommentService;

    @Override
    public void add(@RequestBody DiaryComment diaryComment) {

        diaryCommentService.add(diaryComment);
    }

    @Override
    public List<DiaryComment> getByDiaryId(Integer diaryId) {

        return diaryCommentService.getByDiaryId(diaryId);
    }

    @Override
    public List<DiaryComment> ListByRespondentId(Integer respondentId) {

        return diaryCommentService.ListByRespondentId(respondentId);
    }


}
