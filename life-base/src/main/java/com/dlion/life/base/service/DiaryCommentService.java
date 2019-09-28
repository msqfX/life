package com.dlion.life.base.service;

import com.dlion.life.base.dao.DiaryCommentMapper;
import com.dlion.life.base.entity.DiaryComment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@Service
public class DiaryCommentService {

    @Autowired
    private DiaryCommentMapper diaryCommentMapper;

    public void add(DiaryComment diaryComment) {

        diaryCommentMapper.insertSelective(diaryComment);
    }

    public List<DiaryComment> getByDiaryId(Integer diaryId) {

        return diaryCommentMapper.selectByDiaryId(diaryId);
    }

    public List<DiaryComment> ListByRespondentId(Integer respondentId) {

        return diaryCommentMapper.ListByRespondentId(respondentId);
    }
}
