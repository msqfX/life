package com.dlion.life.user.model;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 李正元
 * @date 2019/9/23
 */

@Data
public class UserHomePageModel {

    private Integer id;

    private String openId;

    private String nickName;

    /**
     * 用户头像图片的 URL。URL 最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640x640 的正方形头像，
     * 46 表示 46x46 的正方形头像，剩余数值以此类推。默认132），用户没有头像时该项为空。若用户更换头像，原有头像 URL 将失效。
     */
    private String avatarUrl;

    /**
     * 用户性别
     * 0	未知
     * 1	男性
     * 2	女性
     */
    private Integer gender;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 个性签名
     */
    private String personSign;

    /**
     * 个人页面访问量
     */
    private Integer personalPageVisitNum;

}
