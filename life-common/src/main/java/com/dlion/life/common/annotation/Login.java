package com.dlion.life.common.annotation;

import java.lang.annotation.*;

/**
 * 校验用户登录
 *
 * @author 李正元
 * @date 2019/10/8
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Login {
}
