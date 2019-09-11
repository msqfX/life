package com.dlion.life.base.dao;

import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PunchCardDiaryMapper extends BaseMapper {

    List<PunchCardDiary> selectByProjectId(@Param("projectId") Integer projectId);
}