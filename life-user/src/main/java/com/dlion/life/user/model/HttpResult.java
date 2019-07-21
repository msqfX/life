package com.dlion.life.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 李正元
 * @date 2019-07-21
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class HttpResult {

    private Integer code;


    private String body;

}
