package com.dlion.life.user.model;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/28
 */
@Data
public class DiaryCommentModel {

    private Integer id;

    private Integer diaryId;

    private Integer respondentId;

    private String textComment;

    private String createTime;

    private ReviewerModel reviewer;

}
