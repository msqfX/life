package com.dlion.life.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 客服消息接口
 *
 * @author 李正元
 * @date 2019/10/8
 */
@RestController
@RequestMapping("/api/message")
public class CustomerController {

    @PostMapping
    public Object message() {


        return "success";
    }


}
