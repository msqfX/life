package com.dlion.life.base.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class Water {

    private Integer id;

    private Integer userId;

    private BigDecimal amount;

    private Integer accountId;

    private Integer changeType;

    private Integer consumptionType;

    private String remark;

    private Date createTime;

    private Date updateTime;

}