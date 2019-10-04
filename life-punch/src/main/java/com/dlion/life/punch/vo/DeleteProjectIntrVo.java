package com.dlion.life.punch.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 李正元
 * @date 2019/10/4
 */
@Data
public class DeleteProjectIntrVo {

    @NotNull(message = "参数异常")
    private Integer projectId;

    @NotNull(message = "参数异常")
    private Integer[] deleteIdList;

}
