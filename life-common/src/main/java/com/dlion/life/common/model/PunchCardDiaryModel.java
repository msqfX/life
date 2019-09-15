package com.dlion.life.common.model;

import lombok.Data;

import java.util.Date;

/**
 * 打卡日历
 *
 * @author 李正元
 * @date 2019/9/11
 */
@Data
public class PunchCardDiaryModel {

    private Integer id;

    private Integer projectId;

    private Integer userId;

    private String textContent;

    private Date punchCardTime;

    private String punchCardAddress;

    private Double addressLongitude;

    private Double addressLatitude;

    private Integer visibleType;

    private Integer currDiaryPunchCardDayNum;

    private Integer haveSticky;

    private Integer isRepairDiary;

    private Date repairPunchCardTime;

    private Integer likeUserNum;

    private Integer commentNum;
}
