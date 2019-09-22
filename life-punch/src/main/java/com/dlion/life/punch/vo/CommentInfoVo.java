package com.dlion.life.punch.vo;

import com.dlion.life.punch.model.AttendUserModel;
import lombok.Data;

/**
 * @author 李正元
 * @date 2019-09-21
 */
@Data
public class CommentInfoVo {

    private Integer id;

    private Integer diaryId;

    private Integer pid;

    private Integer reviewerId;

    private Integer respondentId;

    private String textComment;

    private String soundComment;

    private String createTime;

    private AttendUserModel reviewer;

    private AttendUserModel respondent;
}
