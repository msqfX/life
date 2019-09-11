package com.dlion.life.punch.controller;

import com.dlion.life.base.api.DiaryCommentApi;
import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.common.model.DiaryCommentModel;
import com.dlion.life.common.model.ResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 打卡日历评论
 *
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/diaryComment")
public class DiaryCommentController {

    @Autowired
    private DiaryCommentApi diaryCommentApi;

    @PostMapping
    public Object add(DiaryCommentModel diaryCommentModel) {

        DiaryComment diaryComment = new DiaryComment();

        BeanUtils.copyProperties(diaryCommentModel, diaryComment);

        diaryCommentApi.add(diaryComment);

        return new ResponseModel();
    }

    @GetMapping("/getByDiaryId/{diaryId}")
    public Object getByDiaryId(@PathVariable Integer diaryId) {

        List<DiaryComment> commentList = diaryCommentApi.getByDiaryId(diaryId);

        List<DiaryCommentModel> modelList = commentList.stream().map(comment -> {

            DiaryCommentModel diaryCommentModel = new DiaryCommentModel();

            BeanUtils.copyProperties(comment, diaryCommentModel);

            return diaryCommentModel;
        }).collect(Collectors.toList());

        return new ResponseModel();
    }

}
