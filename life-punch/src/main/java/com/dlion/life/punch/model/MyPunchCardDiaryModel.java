package com.dlion.life.punch.model;

import com.dlion.life.common.vo.DiaryResourceVo;
import com.dlion.life.common.vo.Publisher;
import com.dlion.life.common.vo.PunchCardProjectVo;
import lombok.Data;

import java.util.List;

/**
 * 我打打卡日记（我的个人中心）
 *
 * @author 李正元
 * @date 2019/9/15
 */
@Data
public class MyPunchCardDiaryModel {

    private Integer id;

    private String textContent;

    private String punchCardTime;

    private String punchCardAddress;

    private Double addressLongitude;

    private Double addressLatitude;

    private Integer visibleType;

    private Integer currDiaryPunchCardDayNum;

    private Integer haveSticky;

    private Integer isRepairDiary;

    private Integer likeUserNum;

    private Integer commentNum;

    private Boolean haveLike;

    private Integer likeRecordId;

    private PunchCardProjectVo punchCardProject;

    private List<DiaryResourceVo> diaryResource;

    private Publisher publisher;

    private List tenLikeInfo;

    private List allCommentInfo;

}
