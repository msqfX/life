package com.dlion.life.base.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

/**
 * @author 李正元
 * @date 2019-07-21
 */
@Data
public class Account {

    /**
     * 账户ID
     */
    private Integer id;

    private Integer userId;

    /**
     * 账户名称
     */
    private String name;

    /**
     * 账户类型
     */
    private Integer accountType;

    /**
     * 固定额度
     */
    private BigDecimal amount;

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
     * 卡号
     */
    private String cardNum;

    /**
     * 备注
     */
    private String remark;

    /**
     * 最近资金变化:0.00
     */
    private BigDecimal changes;

    private Timestamp createTime;

    private Timestamp updateTime;


}
