package com.dlion.life.base.dao;

import com.dlion.life.base.entity.UnreadNewsCount;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface UnreadNewsCountMapper extends BaseMapper<UnreadNewsCount, Integer> {

    List<UnreadNewsCount> selectByUserId(@Param("userId") Integer userId);

    void setNewsCount(@Param("userId") Integer userId, @Param("unreadNewsType") String unreadNewsType, @Param("num") int num);

    void addNewsCount(@Param("userId") Integer userId, @Param("unreadNewsType") String unreadNewsType);
}