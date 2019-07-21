package com.dlion.life.base.dao;

import com.dlion.life.base.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Mapper
public interface UserMapper {

    int inserUser(@Param("user") User user);

    User selectUserById(Integer id);

    int updateUser(User user);

    User selectByOpenId(@Param("openId") String openId);
}
