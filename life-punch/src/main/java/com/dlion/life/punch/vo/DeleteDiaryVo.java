package com.dlion.life.punch.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 打卡日记删除
 *
 * @author 李正元
 * @date 2019/10/5
 */
@Data
public class DeleteDiaryVo {

    @NotNull(message = "参数异常")
    private Integer projectId;

    @NotNull(message = "参数异常")
    private Integer diaryId;

    @NotNull(message = "参数异常")
    private Integer userId;

}
