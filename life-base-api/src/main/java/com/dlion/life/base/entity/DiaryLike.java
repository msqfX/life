package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Data
public class DiaryLike {

    private Integer id;

    private Integer diaryId;

    private Integer likedUserId;

    private Integer admirerId;

    private Date createTime;

}