package com.dlion.life.base.controller;

import com.dlion.life.base.api.UserApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@RestController
public class UserController implements UserApi {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/hello")
    @Override
    public String hello(@RequestParam String name) {
        logger.info("invoked name = " + name);
        return "hello " + name+"port:"+applicationContext.getEnvironment().getProperty("server.port");
    }

    @GetMapping
    public String getUserInfo(@RequestParam("userId") String userId){
        return userId;
    }

}
