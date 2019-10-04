package com.dlion.life.punch.vo;

import lombok.Data;

/**
 * 更新圈子背景图片
 *
 * @author 李正元
 * @date 2019/10/3
 */
@Data
public class UpdateCoverImgVo {

    private Integer preSysRecommendCoverImgId;

    private Integer newSysRecommendCoverImgId;

    private String newImgUrl;

    private String curCoverImgUrl;

    private Integer projectId;

}
