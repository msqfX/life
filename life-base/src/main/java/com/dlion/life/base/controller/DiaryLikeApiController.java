package com.dlion.life.base.controller;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.service.DiaryLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@RestController
public class DiaryLikeApiController implements DiaryLikeApi {

    @Autowired
    private DiaryLikeService diaryLikeService;

    @Override
    public DiaryLike getByDiaryIdAndUserId(Integer diaryId, Integer userId) {
        return diaryLikeService.getByDiaryIdAndUserId(diaryId, userId);
    }
}
