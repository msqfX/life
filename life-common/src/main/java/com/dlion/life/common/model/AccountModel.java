package com.dlion.life.common.model;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 账户列表详情model
 *
 * @author 李正元
 * @date 2019-07-20
 */

@Data
public class AccountModel {

    /**
     * 账户ID
     */
    private Integer id;

    private Integer userId;

    /**
     * 简称
     */
    private String abbreviation;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 固定额度
     */
    private BigDecimal Amount;

    /**
     * 已使用额度
     */
    private BigDecimal userdAmount;

    /**
     * 可用额度
     */
    private BigDecimal usableAmount;

    /**
     * 账单日
     */
    private Date billingDay;

    /**
     * 还款日
     */
    private Date repaymentDate;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 账户类型名称
     */
    private String accountTypeName;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最近资金变化:0.00
     */
    private BigDecimal changes;

}
