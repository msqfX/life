package com.dlion.life.base.dao;

import com.dlion.life.base.entity.UserProjectRecord;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Mapper
public interface UserProjectRecordMapper extends BaseMapper<UserProjectRecord, Integer> {

    /**
     * 根据用户id查询当前参与的圈子
     *
     * @param userId
     * @return
     */
    List<UserProjectRecord> selectByUserId(@Param("userId") Integer userId);


}