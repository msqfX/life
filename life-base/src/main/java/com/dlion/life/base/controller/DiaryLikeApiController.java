package com.dlion.life.base.controller;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.service.DiaryLikeService;
import com.dlion.life.common.bo.DiarySearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @Override
    public List<DiaryLike> listByDiaryId(Integer diaryId) {

        return diaryLikeService.listByDiaryId(diaryId);
    }

    @Override
    public Integer like(@RequestBody DiaryLike diaryLike) {

        return diaryLikeService.like(diaryLike);
    }

    @Override
    public void delete(Integer likeRecordId) {

        diaryLikeService.delete(likeRecordId);
    }

    @Override
    public List<DiaryLike> listByAdmirerId(Integer admirerId) {

        return diaryLikeService.listByAdmirerId(admirerId);
    }

    @Override
    public List<DiaryLike> listByLikedUserId(Integer likedUserId) {

        return diaryLikeService.listByLikedUserId(likedUserId);
    }
}
