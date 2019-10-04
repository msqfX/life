package com.dlion.life.punch.model;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 圈子描述信息
 *
 * @author 李正元
 * @date 2019/9/15
 */
@Data
public class ProIntrDetailInfoModel {

    @NotNull(message = "参数异常")
    private Integer id;

    private Integer projectId;

    private Integer type;

    private Integer sort;

    private String content;

}
