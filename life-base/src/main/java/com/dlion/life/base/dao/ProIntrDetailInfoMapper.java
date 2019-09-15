package com.dlion.life.base.dao;

import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface ProIntrDetailInfoMapper extends BaseMapper<ProIntrDetailInfo, Integer> {

    List<ProIntrDetailInfo> selectByProjectId(@Param("projectId") Integer projectId);

}