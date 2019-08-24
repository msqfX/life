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

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 支出/收入
     */
    private Integer changeType;

    /**
     * 消费类型
     */
    private Integer consumptionType;

    /**
     * 备注
     */
    private String remark;

}
