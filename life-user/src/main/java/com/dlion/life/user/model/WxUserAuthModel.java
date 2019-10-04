package com.dlion.life.user.model;

import lombok.Data;

/**
 * 用户认证信息
 *
 * @author 李正元
 * @date 2019/10/4
 */
@Data
public class WxUserAuthModel {

    private String openId;

    private String sessionKey;

    private String unionId;

    private String token;
}
