package com.dlion.life.base.dao;

import com.dlion.life.base.entity.ProjectTypeLabel;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface ProjectTypeLabelMapper extends BaseMapper<ProjectTypeLabel, Integer> {

    List<ProjectTypeLabel> listByType(@Param("type") Integer type);

    List<ProjectTypeLabel> listByParentLabelName(@Param("parentLabelName") String parentLabelName);
}