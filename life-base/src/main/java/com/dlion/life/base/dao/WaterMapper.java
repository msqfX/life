package com.dlion.life.base.dao;

import com.dlion.life.base.entity.Water;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface WaterMapper {

    long countByCon(@Param("water") Water water);

    int deleteById(@Param("id") Integer id);

    int insert(@Param("water") Water water);

    int insertSelective(@Param("water") Water water);

    List<Water> selectByCon(@Param("water") Water water);

    List<Water> selectByTime(@Param("userId") Integer userId, @Param("startTime") Date startTime, @Param("endTime") Date endTime);

}