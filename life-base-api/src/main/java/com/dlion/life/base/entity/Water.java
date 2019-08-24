package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Water {

    private Integer id;

    private Integer userId;

    private Long amount;

    private Byte accountType;

    private Byte changeType;

    private Byte consumptionType;

    private Date createTime;

    private Date updateTime;

}