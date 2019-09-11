package com.dlion.life.user.service;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

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

    /**
     * 发送 res.code 到后台换取 openId, sessionKey, unionId
     *
     * @param code
     * @return
     */
    public Map<String, Object> getWxUserInfo(String code) {

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";

        url = String.format(url, appId, secret, code);

        Map<String, Object> result = new HashMap<>(3);

        logger.info("获取用户身份信息result:{}", result);

        try {
            String res = httpService.doGet(url);

            JSONObject json = (JSONObject) JSONObject.parse(res);

            result.put("openId", json.getString("openid"));
            result.put("sessionKey", json.getString("session_key"));
            result.put("unionId", json.getString("unionid"));

        } catch (Exception e) {
            logger.error("获取用户openId失败 code:{}", code, e);
        }

        return result;
    }


}
