package com.dlion.life.gateway.auth;

import com.dlion.life.common.constant.RedisKey;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/10/4
 */
@Service
public class TokenUtil {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public boolean verify(String token) {

        if (StringUtils.isEmpty(token)) {
            return false;
        }

        String key = String.format(RedisKey.USER_AUTH_TOKEN, token);

        Object obj = redisTemplate.opsForValue().get(key);
        if (Objects.isNull(obj)) {
            return false;
        }

        return true;
    }

}
