package com.dlion.life.punch.service;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Service
public class PunchCardDiaryService {

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    public Boolean hasLike(Integer punchCardDiaryId, Integer userId) {

        DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(punchCardDiaryId, userId);

        return Objects.nonNull(diaryLike);
    }

    public void updateComment(PunchCardDiary punchCardDiary) {

        if (Objects.isNull(punchCardDiary)) {
            return;
        }

        punchCardDiaryApi.update(punchCardDiary);
    }


}
