package com.dlion.life.common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 打卡日记查询参数
 *
 * @author 李正元
 * @date 2019/9/14
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PunchCardDiarySearch {

    /**
     * 被查看打卡日记列表的用户的id
     */
    private Integer visitedUserId;

    /**
     * 是否为创建者：0：否，1：是
     */
    private Integer isDiaryCreator;

    private Integer pageNo;

    private Integer dataNum;

}
