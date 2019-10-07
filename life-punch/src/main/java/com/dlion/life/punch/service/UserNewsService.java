package com.dlion.life.punch.service;

import com.dlion.life.common.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

/**
 * 用户消息服务ø
 *
 * @author 李正元
 * @date 2019/10/6
 */
@Service
public class UserNewsService {

    @Autowired
    private HashOperations<String, String, Object> hashOperations;


    public void setNewsCount(Integer userId) {

        hashOperations.increment(RedisKey.USER_COMMENT_NEWS_COUNT, userId.toString(), 1);
    }

    public void setLikeCount(Integer userId) {

        hashOperations.increment(RedisKey.USER_LIKE_NEWS_COUNT, userId.toString(), 1);
    }

}
