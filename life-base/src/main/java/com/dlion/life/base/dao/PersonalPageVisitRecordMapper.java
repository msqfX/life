package com.dlion.life.base.dao;

import com.dlion.life.base.entity.PersonalPageVisitRecord;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface PersonalPageVisitRecordMapper extends BaseMapper<PersonalPageVisitRecord, Integer> {

    List<PersonalPageVisitRecord> getByUserId(@Param("visitedId") Integer visitedId);
}