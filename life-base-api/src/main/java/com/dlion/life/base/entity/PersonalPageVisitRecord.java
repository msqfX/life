package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class PersonalPageVisitRecord {

    private Integer id;

    private Integer visitorId;

    private Integer visitedId;

    private Date createTime;

    private Date updateTime;
}