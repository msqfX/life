package com.dlion.life.base.entity;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Data
public class PunchCardProject {

    private Integer id;

    private String projectName;

    private Integer privacyType;

    private Integer openAudit;

    private String typeLabel;

    private String coverImgUrl;

    private Integer creatorId;

    private String creatorIntroduce;

    private String weixinNum;

    private Integer allPunchCardNum;

    private Integer todayPunchCardNum;

    private Integer attendUserNum;

}