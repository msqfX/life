package com.dlion.life.common.enums;

import java.util.Objects;

/**
 * 账户类型
 *
 * @author 李正元
 * @date 2019-07-18
 */

public enum AccountTypeEnum {

    /**
     * 在线支付
     */
    ONLINE_PAYMENT(1,"在线支付"),

    /**
     * 储蓄卡
     */
    DEBIT_CARD(2,"储蓄卡"),

    /**
     * 信用卡
     */
    CREDIT_CARD(3,"信用卡"),

    /**
     * 储值卡
     */
    STORED_VALUE_CARD(4,"储值卡"),

    /**
     * 现金账户
     */
    CASH_ACCOUNT(5,"现金账户");

    /**
     * id
     */
    private Integer id;

    /**
     * 名称
     */
    private String name;


    AccountTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public static String getName(Integer id) {
        for (AccountTypeEnum accountType : values()) {
            if (Objects.equals(accountType.id, id)) {
                return accountType.name;
            }
        }
        return null;
    }


}
