package com.dlion.life.base.entity;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * 用户信息
 *
 * @author 李正元
 * @date 2019/7/19
 */
@Data
public class User {

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

    private String country;

    private String province;

    private String city;

    /**
     * en	英文
     * zh_CN	简体中文
     * zh_TW	繁体中文
     */
    private String language;

    /**
     * 预留字段，扩展其他渠道
     */
    private String passWord;

    /**
     * 预留字段，扩展其他渠道
     */
    private String userName;

    /**
     * 渠道
     */
    private String channel;

    /**
     * 设备品牌
     */
    private String brand;

    /**
     * 设备型号
     */
    private String model;

    /**
     * 微信设置的语言
     */
    private String wxLanguage;

    /**
     * 操作系统及版本
     */
    private String system;

    /**
     * 客户端平台
     */
    private String platform;
    /**
     * 生日
     */
    private Date birthday;

    /**
     * 性别
     */
    private Byte sex;

    /**
     * 个性签名
     */
    private String personSign;

    /**
     * 北京图片
     */
    private Integer bgImg;

    /**
     * 个人页面访问量
     */
    private Integer personalPageVisitNum;

    private Timestamp createTime;

    private Timestamp updateTime;

}
