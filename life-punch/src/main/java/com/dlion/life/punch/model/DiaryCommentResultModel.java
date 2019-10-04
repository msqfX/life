package com.dlion.life.punch.model;

import com.dlion.life.punch.vo.ReviewerVo;
import lombok.Data;

import javax.validation.constraints.NotNull;


/**
 * @author 李正元
 * @date 2019/10/4
 */
@Data
public class DiaryCommentResultModel {

    private Integer id;

    @NotNull(message = "参数异常")
    private Integer diaryId;

    private Integer pid;

    private Integer reviewerId;

    private Integer respondentId;

    private String textComment;

    private String soundComment;

    private String createTime;

    private ReviewerVo reviewer;

    private ReviewerVo respondent;

}
