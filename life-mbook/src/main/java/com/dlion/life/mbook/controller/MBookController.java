package com.dlion.life.mbook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019-07-16
 */

@RestController("/")
public class MBookController {

    @GetMapping("/test")
    public String test(){
        return "succes";
    }

}
