package com.dlion.life.common.model;

import com.dlion.life.common.constant.ResultConstant;
import lombok.Data;

/**
 * 接口返回信息
 *
 * @author 李正元
 * @date 2019-07-19
 */

@Data
public class ResponseModel {

    private Integer code;

    private String msg;

    private Object data;

    public ResponseModel() {
        this.code = ResultConstant.SUCCESS;
        this.msg = ResultConstant.SUCCESS_MSG;
    }

    public ResponseModel(Object data) {
        this.code = ResultConstant.SUCCESS;
        this.msg = ResultConstant.SUCCESS_MSG;
        this.data = data;
    }

    public ResponseModel(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }


}
