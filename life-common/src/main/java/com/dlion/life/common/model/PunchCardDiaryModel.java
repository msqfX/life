package com.dlion.life.common.model;

import java.util.Date;

/**
 * 打卡日历
 *
 * @author 李正元
 * @date 2019/9/11
 */
public class PunchCardDiaryModel {

    private Integer id;

    private Integer projectId;

    private Integer userId;

    private String textContent;

    private Date punchCardTime;

    private String punchCardAddress;

    private Double addressLongitude;

    private Double addressLatitude;

    private Byte visibleType;

    private Integer currDiaryPunchCardDayNum;

    private Byte haveSticky;

    private Byte isRepairDiary;

    private Date repairPunchCardTime;

    private Integer likeUserNum;

    private Integer commentNum;
}
