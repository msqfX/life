package com.dlion.life.common.vo;

import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/12
 */
@Data
public class ProjectInfo {

    private Integer id;

    private String projectName;

    private List<TypeLabel> typeLabel;

    private String coverImgUrl;

    private Integer attendUserNum;

}
