package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

@Data
public class SysRecommendBgImg {

    private Integer id;

    private String bgImgUrl;

    private Byte isRecommend;

    private Integer useNum;

    private Date createTime;

    private Date updateTime;

    public Integer getId() {
        return id;
    }

}