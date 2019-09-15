package com.dlion.life.punch.model;

import lombok.Data;

/**
 * 发现页推荐打卡圈子
 *
 * @author 李正元
 * @date 2019/9/14
 */
@Data
public class ProjectListRecommendModel {

    /**
     * 总打卡人数
     */
    private Integer allPunchCardNum;

    /**
     * 参数人数
     */
    private Integer attendUserNum;

    /**
     * 图片地址
     */
    private String coverImgUrl;

    /**
     * 创建人
     */
    private Integer creatorId;

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 名称
     */
    private String projectName;

}
