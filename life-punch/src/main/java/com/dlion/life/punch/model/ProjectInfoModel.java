package com.dlion.life.punch.model;

import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@Data
public class ProjectInfoModel {

    private Integer id;

    private String projectName;

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

    private Integer creatorGender;

    private String creatorNickName;

    private String creatorAvatarUrl;

    private List<ProIntrDetailInfoModel> projectIntrInfo;

    private List<AttendUserModel> attendUserList;

}
