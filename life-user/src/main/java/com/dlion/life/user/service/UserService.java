package com.dlion.life.user.service;

import com.alibaba.fastjson.JSONObject;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.CharacterConstant;
import com.dlion.life.common.constant.RedisKey;
import com.dlion.life.user.model.WxUserAuthModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 用户业务类
 *
 * @author 李正元
 * @date 2019-07-21
 */
@Service
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpService httpService;

    @Value("${wxmp.appId}")
    private String appId;

    @Value("${wxmp.secret}")
    private String secret;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserApi userApi;

    /**
     * 发送 res.code 到后台换取 openId, sessionKey, unionId
     *
     * @param code
     * @return
     */
    public WxUserAuthModel getWxUserInfo(String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

        url = String.format(url, appId, secret, code);
        WxUserAuthModel model = new WxUserAuthModel();
        try {
            String res = httpService.doGet(url);

            JSONObject json = (JSONObject) JSONObject.parse(res);

            logger.info("获取用户身份信息result:{}", json);

            model.setOpenId(json.getString("openid"));
            model.setSessionKey(json.getString("session_key"));
            model.setUnionId(json.getString("unionid"));

            //使用session_key与openID生产token
            String originStr = json.getString("openid") + "_" + json.getString("session_key");
            String token = DigestUtils.md5DigestAsHex(originStr.getBytes());
            model.setToken(token);
            String redisKey = String.format(RedisKey.USER_AUTH_TOKEN, token);
            redisTemplate.opsForValue().set(redisKey, json.getString("openid"), RedisKey.USER_TOKEN_CACHE_EXPIRE_TIME,
                    TimeUnit.MINUTES);

        } catch (Exception e) {
            logger.error("获取用户openId失败 code:{}", code, e);
        }

        return model;
    }

    /**
     * 缓存用户信息
     *
     * @param token 用户认证tokenø
     */
    public void setUserInfoCache(String token) {

        String key = String.format(RedisKey.USER_AUTH_TOKEN, token);
        Object obj = redisTemplate.opsForValue().get(key);

        String openId = obj.toString();

        User user = userApi.getByOpenId(openId);
        if (Objects.nonNull(user)) {
            redisTemplate.opsForValue().set(token, user, RedisKey.USER_TOKEN_CACHE_EXPIRE_TIME, TimeUnit.MINUTES);
        }

    }
}
