package com.dlion.life.base.dao;

import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface PunchCardDiaryMapper extends BaseMapper<PunchCardDiary, Integer> {

    List<PunchCardDiary> selectByProjectId(@Param("projectId") Integer projectId);

    List<PunchCardDiary> listByUserId(@Param("userId") Integer userId, @Param("pageNo") Integer pageNo,
                                      @Param("dataNum") Integer dataNum, @Param("isDiaryCreato") Integer isDiaryCreato);

    List<PunchCardDiary> listByCno(@Param("projectId") Integer projectId,
                                   @Param("pageNo") Integer pageNo,
                                   @Param("dataNum") Integer dataNum);
}