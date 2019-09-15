package com.dlion.life.common.model;

import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/12
 */
@Data
public class PunchCardProjectListHomeModel {

    private Integer id;

    private String nickName;

    private Integer gender;

    private List<PunchCardProjectListModel> punchCardProjectList;

}
