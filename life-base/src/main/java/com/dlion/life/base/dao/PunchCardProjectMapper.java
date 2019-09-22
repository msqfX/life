package com.dlion.life.base.dao;

import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Mapper
public interface PunchCardProjectMapper extends BaseMapper<PunchCardProject, Integer> {


    /**
     * 根据用户id圈子
     *
     * @param userId 用户id
     * @return
     */
    List<PunchCardProject> selectByUserId(@Param("userId") Integer userId);

    /**
     * 获取打卡数超过一定数量的圈子
     *
     * @param num 打卡数
     * @return
     */
    List<PunchCardProject> getMorePunchCard(@Param("num") Integer num,
                                            @Param("pageNo") Integer pageNo,
                                            @Param("dataNum") Integer dataNum
    );

    /**
     * 根据关键字查询
     *
     * @param keyword 关键字
     * @param dataNum 页码
     * @param pageNo  条数
     * @return
     */
    List<PunchCardProject> search(@Param("keyword") String keyword,
                                  @Param("pageNo") Integer pageNo,
                                  @Param("dataNum") Integer dataNum);


    List<PunchCardProject> getProjectListByType(@Param("typeName") String typeName,
                                                @Param("pageNo") Integer pageNo,
                                                @Param("pageSize") Integer pageSize);
}