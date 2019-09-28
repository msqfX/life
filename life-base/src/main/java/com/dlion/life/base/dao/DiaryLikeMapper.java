package com.dlion.life.base.dao;

import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface DiaryLikeMapper extends BaseMapper<DiaryLike, Integer> {

    DiaryLike seleteByDiaryIdAndUserId(@Param("diaryId") Integer diaryId, @Param("userId") Integer userId);


    List<DiaryLike> listByDiaryId(@Param("diaryId") Integer diaryId);

    List<DiaryLike> listByAdmirerId(@Param("admirerId") Integer admirerId);
}