package com.dlion.life.common.enums;

import java.util.Objects;

/**
 * 消费类型
 *
 * @author 李正元
 * @date 2019-08-25
 */

public enum ConsumptionTypeEnum {

    /**
     * 餐饮
     */
    CANYIN(40,"餐饮","icon-canyin");


    private Integer type;

    private String name;

    private String img;

    ConsumptionTypeEnum(Integer type, String name, String img) {

        this.type = type;
        this.name = name;
        this.img = img;
    }

    public static String getNameByType(Integer type) {

        for (ConsumptionTypeEnum consumptionTypeEnum : values()) {
            if (Objects.equals(type, consumptionTypeEnum.type)) {
                return consumptionTypeEnum.name;
            }
        }

        return null;
    }

    public static String getImgByType(Integer type) {

        for (ConsumptionTypeEnum consumptionTypeEnum : values()) {
            if (Objects.equals(type, consumptionTypeEnum.type)) {
                return consumptionTypeEnum.img;
            }
        }

        return null;
    }

    public static ConsumptionTypeEnum getInstance(Integer type){

        for (ConsumptionTypeEnum consumptionTypeEnum : values()) {
            if (Objects.equals(type, consumptionTypeEnum.type)) {
                return consumptionTypeEnum;
            }
        }

        return null;
    }



}
