package com.dlion.life.base.service;

import com.dlion.life.base.dao.UserMapper;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Service
public class UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user) {
        userMapper.inserUser(user);
    }

    public User getUser(Integer id) {
        return userMapper.selectUserById(id);
    }

    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    public User getByOpenId(String openId) {

        String key = String.format(RedisKey.USER_INFO_OPENID, openId);

        User user = (User) redisTemplate.opsForValue().get(key);

        if (Objects.isNull(user)) {
            user = userMapper.selectByOpenId(openId);
            redisTemplate.opsForValue().set(key, user);
        }

        return user;
    }


}
