package com.dlion.life.common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 李正元
 * @date 2019/10/5
 */
@Data
@AllArgsConstructor
public class DiarySearchPo {

    private Integer likeUserNum;

    private Integer commentNum;

    private Integer visibleType;

    private Integer projectId;

    private Integer pageNo;

    private Integer dataNum;

}
