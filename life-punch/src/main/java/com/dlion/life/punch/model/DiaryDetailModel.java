package com.dlion.life.punch.model;

import com.dlion.life.common.vo.DiaryResourceVo;
import com.dlion.life.common.vo.Publisher;
import com.dlion.life.punch.vo.CommentInfoVo;
import com.dlion.life.punch.vo.DiaryLikeInfoVo;
import com.dlion.life.punch.vo.DiaryProjectInfoVo;
import lombok.Data;

import java.util.List;

/**
 * 日记详情
 *
 * @author 李正元
 * @date 2019/9/22
 */
@Data
public class DiaryDetailModel {

    private Integer id;

    private String textContent;

    private String punchCardTime;

    private String punchCardAddress;

    private Double addressLongitude;

    private Double addressLatitude;

    private Integer currDiaryPunchCardDayNum;

    private Integer likeUserNum;

    private Integer commentNum;

    private DiaryProjectInfoVo projectInfo;

    private boolean haveLike;

    private boolean existAttendProject;

    private Integer likeRecordId;

    private List<DiaryResourceVo> diaryResource;

    private Publisher publisher;

    private List<DiaryLikeInfoVo> allLikeInfo;

    private List<CommentInfoVo> allCommentInfo;

}
