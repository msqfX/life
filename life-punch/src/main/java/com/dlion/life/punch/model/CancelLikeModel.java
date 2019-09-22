package com.dlion.life.punch.model;

import lombok.Data;

/**
 * 取消喜欢参数对象
 *
 * @author 李正元
 * @date 2019/9/22
 */
@Data
public class CancelLikeModel {

    private Integer diaryId;

    private Integer likeRecordId;
}
