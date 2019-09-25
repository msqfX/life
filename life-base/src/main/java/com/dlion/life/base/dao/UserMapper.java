package com.dlion.life.base.dao;

import com.dlion.life.base.entity.User;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Mapper
public interface UserMapper extends BaseMapper<User, Integer> {

    User selectByOpenId(@Param("openId") String openId);

    List<User> list(@Param("userName") String userName,
                    @Param("pageNum") Integer pageNum,
                    @Param("pageSize") Integer pageSize);
}
