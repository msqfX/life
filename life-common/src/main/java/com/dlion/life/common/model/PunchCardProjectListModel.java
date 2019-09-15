package com.dlion.life.common.model;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/12
 */
@Data
public class PunchCardProjectListModel {

    private Integer id;

    private String projectName;

    private String coverImgUrl;

    private Integer creatorId;

    private Integer isCreator;

}
