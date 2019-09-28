package com.dlion.life.user.model;

import lombok.Data;

/**
 * @author 李正元
 * @date 2019/9/28
 */
@Data
public class DiaryLikeModel {

    private Integer id;

    private Integer diaryId;

    private String creteTime;

    private AdmirerModel admirer;

}
