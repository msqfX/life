package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author 李正元
 * @date 2019-09-15
 */
@Data
public class DiaryResource {

    private Integer id;

    private Integer diaryId;

    private String resourceUrl;

    private Integer type;

    private Date createTime;

}