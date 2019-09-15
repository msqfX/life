package com.dlion.life.common.model;

import lombok.Data;

/**
 * 打卡项目
 *
 * @author 李正元
 * @date 2019/9/11
 */
@Data
public class PunchCardProjectModel {

    private Integer id;

    private String projectName;

    private Integer privacyType;

    private Integer openAudit;

    private String typeLabel;

    private String coverImgUrl;

    private Integer creatorId;

    private String creatorIntroduce;

    private String weixinNum;

    /**
     * 总打卡人数
     */
    private Integer allPunchCardNum;

    private Integer todayPunchCardNum;

    /**
     * 参数人数
     */
    private Integer attendUserNum;

}
