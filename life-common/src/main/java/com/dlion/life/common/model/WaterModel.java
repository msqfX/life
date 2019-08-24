package com.dlion.life.common.model;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 账户流水
 *
 * @author 李正元
 * @date 2019/8/23
 */
@Data
public class WaterModel {

    private Integer id;

    private Integer userId;

    private BigDecimal amount;

    private Integer accountType;

    private Integer changeType;

    private Integer consumptionType;

}
