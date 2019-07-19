package com.dlion.life.base.entity;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Data
public class Note {

    private Integer id;

    private Integer userId;

    private String content;

    private Timestamp createTime;

    private Timestamp updateTime;

}
