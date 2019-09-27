package com.dlion.life.user.model;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/23
 */
@Data
public class OtherUserVisitInfoModel {

    private Integer id;

    private Integer visitedId;


    private String createTime;

    private VisitorInfoModel visitorInfo;

}
