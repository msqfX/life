package com.dlion.life.punch.vo;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/22
 */
@Data
public class DiaryProjectInfoVo {

    private Integer id;

    private String projectName;

    private String coverImgUrl;

    /**
     * 总打卡人数
     */
    private Integer allPunchCardNum;

    /**
     * 参数人数
     */
    private Integer attendUserNum;

}
