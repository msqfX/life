package com.dlion.life.base.entity;

import lombok.Data;

import java.util.Date;

/**
 * 参与的打卡记录
 */
@Data
public class UserProjectRecord {

    private Integer id;

    private Integer userId;

    private Integer projectId;

    private Byte isCreator;

    private Byte attendStatus;

    private Integer allPunchCardNum;

    private Date attendTime;

    private Date lastPunchCardTime;

}