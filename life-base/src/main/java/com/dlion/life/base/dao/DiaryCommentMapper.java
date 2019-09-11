package com.dlion.life.base.dao;

import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiaryCommentMapper extends BaseMapper {

    List<DiaryComment> selectByDiaryId(@Param("diaryId") Integer diaryId);

}