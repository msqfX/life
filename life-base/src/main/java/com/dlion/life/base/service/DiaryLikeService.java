package com.dlion.life.base.service;

import com.dlion.life.base.dao.DiaryLikeMapper;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.bo.DiarySearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@Service
public class DiaryLikeService {

    @Autowired
    private DiaryLikeMapper diaryLikeMapper;

    /**
     * 根据日记id和用户id查询
     *
     * @param diaryId
     * @param userId
     * @return
     */
    public DiaryLike getByDiaryIdAndUserId(Integer diaryId, Integer userId) {

        return diaryLikeMapper.seleteByDiaryIdAndUserId(diaryId, userId);
    }

    public List<DiaryLike> listByDiaryId(Integer diaryId) {

        return diaryLikeMapper.listByDiaryId(diaryId);
    }

    public Integer like(DiaryLike diaryLike) {

        diaryLikeMapper.insertSelective(diaryLike);
        return diaryLike.getId();
    }

    public void delete(Integer likeRecordId) {

        diaryLikeMapper.deleteByPrimaryKey(likeRecordId);
    }

    public List<DiaryLike> listByAdmirerId(Integer admirerId) {

        return diaryLikeMapper.listByAdmirerId(admirerId);
    }

    public List<DiaryLike> listByLikedUserId(Integer likedUserId) {

        return diaryLikeMapper.listByLikedUserId(likedUserId);
    }

    public void deleteByDiaryId(Integer diaryId) {

        diaryLikeMapper.deleteByDiaryId(diaryId);
    }
}
