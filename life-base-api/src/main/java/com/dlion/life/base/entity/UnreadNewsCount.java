package com.dlion.life.base.entity;

import lombok.Data;

/**
 * 用户未读消息统计
 *
 * @author 李正元
 * @date 2019/9/13
 */
@Data
public class UnreadNewsCount {

    private Integer id;

    private Integer userId;

    private String unreadNewsType;

    private Integer unreadNewsNum;

}