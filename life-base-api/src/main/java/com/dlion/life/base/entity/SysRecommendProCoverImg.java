package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRecommendProCoverImg {

    private Integer id;

    private String imgUrl;

    private Byte isRecommend;

    private Integer useNum;

    private Date createTime;

    private Date updateTime;

}