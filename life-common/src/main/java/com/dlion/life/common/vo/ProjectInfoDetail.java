package com.dlion.life.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/14
 */
@Data
public class ProjectInfoDetail {

    private Integer id;

    private String projectName;

    private String coverImgUrl;

    private Integer attendUserNum;

    private Integer allPunchCardNum;

    private List<IntrInfo> intrInfoList;

}
