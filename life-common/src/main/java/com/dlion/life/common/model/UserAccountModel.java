package com.dlion.life.common.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 李正元
 * @date 2019-08-03
 */
@Data
@AllArgsConstructor
public class UserAccountModel {

    private Long jifen;

    private Long lianxuday;

    private Long sumday;

    private Long sumcount;

}
