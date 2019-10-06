package com.dlion.life.base.dao;

import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface DiaryCommentMapper extends BaseMapper<DiaryComment, Integer> {

    List<DiaryComment> selectByDiaryId(@Param("diaryId") Integer diaryId);

    List<DiaryComment> ListByRespondentId(@Param("respondentId") Integer respondentId);

    void deleteChildComment(@Param("diaryId") Integer diaryId, @Param("id") Integer id);

    List<DiaryComment> listByPid(@Param("diaryId") Integer diaryId, @Param("id") Integer id);
}