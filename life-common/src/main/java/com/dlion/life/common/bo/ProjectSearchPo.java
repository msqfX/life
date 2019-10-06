package com.dlion.life.common.bo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 圈子搜索
 *
 * @author 李正元
 * @date 2019/10/6
 */
@Data
@AllArgsConstructor
public class ProjectSearchPo {

    private String typeName;

    /**
     * 0：公开，1：隐私
     */
    private Integer privacyType;

    private Integer pageNo;

    private Integer pageSize;

}
