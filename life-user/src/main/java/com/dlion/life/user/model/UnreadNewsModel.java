package com.dlion.life.user.model;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@Data
public class UnreadNewsModel {

    private Integer unreadLikeNewsNum;

    private Integer unreadCommentNewsNum;

    public UnreadNewsModel() {
        this.unreadCommentNewsNum = 0;
        this.unreadLikeNewsNum = 0;
    }

}
