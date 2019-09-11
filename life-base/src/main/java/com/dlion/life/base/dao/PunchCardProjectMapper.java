package com.dlion.life.base.dao;

import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Mapper
public interface PunchCardProjectMapper extends BaseMapper<PunchCardProject, Integer> {


    List<PunchCardProject> selectByUserId(Integer userId);
}