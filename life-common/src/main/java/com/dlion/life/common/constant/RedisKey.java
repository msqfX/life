package com.dlion.life.common.constant;

/**
 * @author 李正元
 * @date 2019-08-03
 */

public class RedisKey {

    public final static String USER_INFO_OPENID = "user:info:%s";

    /**
     * 缓存用户认证的token
     */
    public final static String USER_AUTH_TOKEN = "user:auth:%s";

    /**
     * 用户token缓存信息时间，默认一个小时
     */
    public final static Integer USER_TOKEN_CACHE_EXPIRE_TIME = 60;

}
