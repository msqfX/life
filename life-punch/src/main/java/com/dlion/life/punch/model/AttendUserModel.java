package com.dlion.life.punch.model;

import com.dlion.life.punch.vo.PivotVo;
import lombok.Data;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@Data
public class AttendUserModel {

    private Integer id;

    private String avatarUrl;

    private String nickName;

    private Integer sex;

    private PivotVo pivot;

}
