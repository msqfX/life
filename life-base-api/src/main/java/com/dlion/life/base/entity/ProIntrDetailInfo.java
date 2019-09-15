package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Data
public class ProIntrDetailInfo {

    private Integer id;

    private Integer projectId;

    private String content;

    private Integer type;

    private Integer sort;

    private Date createTime;

    private Date updateTime;

}