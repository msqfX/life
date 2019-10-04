package com.dlion.life.base.service;

import com.dlion.life.base.dao.UserMapper;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.ChannelConstant;
import com.dlion.life.common.constant.UserRedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Integer addUser(User user) {

        userMapper.insertSelective(user);

        return user.getId();
    }

    public User getUser(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void updateUser(User user) {

        userMapper.updateByPrimaryKeySelective(user);

        User userNew = userMapper.selectByPrimaryKey(user.getId());

        if (Objects.equals(userNew.getChannel(), ChannelConstant.WEIXIN_MINIPROGRAM)) {

            String key = String.format(UserRedisKey.USER_INFO_OPENID, userNew.getOpenId());

            Object obj = redisTemplate.opsForValue().get(key);
            if (Objects.nonNull(obj)) {
                redisTemplate.opsForValue().set(key, userNew);
            }
        }
    }

    public User getByOpenId(String openId) {

        String key = String.format(UserRedisKey.USER_INFO_OPENID, openId);

        Object obj = redisTemplate.opsForValue().get(key);

        if (Objects.nonNull(obj)) {
            return (User) obj;
        }

        User user = userMapper.selectByOpenId(openId);

        if (Objects.nonNull(user)) {
            redisTemplate.opsForValue().set(key, user);
        }

        return user;
    }


    public List<User> list(String userName, Integer pageNum, Integer pageSize) {

        pageNum = (pageNum - 1) * pageSize;
        return userMapper.list(userName, pageNum, pageSize);
    }

    public User getUserByToken(String token) {

        Object obj = redisTemplate.opsForValue().get(token);
        if (Objects.nonNull(obj)) {
            return (User) obj;
        }

        return null;
    }
}
