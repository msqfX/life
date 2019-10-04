package com.dlion.life.user.auth;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.dlion.life.base.entity.User;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 下发Token
 *
 * @author 李正元
 * @date 2019/10/4
 */
@Service
public class TokenService {

    public String getToken(User user) {

        Date start = new Date();
        //一小时有效时间

        long currentTime = System.currentTimeMillis() + 60 * 60 * 1000;
        Date end = new Date(currentTime);

        String token = "";

        token = JWT.create().withAudience(user.getId().toString()).withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(user.getOpenId()));

        return token;
    }
}
