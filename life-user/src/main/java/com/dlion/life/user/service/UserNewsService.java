package com.dlion.life.user.service;

import com.dlion.life.common.constant.RedisKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;

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

    public Integer getCommentCount(Integer userId) {

        Object obj = hashOperations.get(RedisKey.USER_COMMENT_NEWS_COUNT, userId.toString());
        if (Objects.nonNull(obj)) {
            return (Integer) obj;
        }
        return 0;
    }

    public void resetCommentCount(Integer userId) {

        hashOperations.put(RedisKey.USER_COMMENT_NEWS_COUNT, userId.toString(), 0);
    }

    public Integer getLikeCount(Integer userId) {

        Object obj = hashOperations.get(RedisKey.USER_LIKE_NEWS_COUNT, userId.toString());
        if (Objects.nonNull(obj)) {
            return (Integer) (Integer) obj;
        }
        return 0;
    }

    public void resetLikeCount(Integer userId) {

        hashOperations.put(RedisKey.USER_LIKE_NEWS_COUNT, userId.toString(), 0);
    }

}
