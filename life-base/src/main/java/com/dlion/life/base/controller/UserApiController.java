package com.dlion.life.base.controller;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@RestController
public class UserApiController implements UserApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Autowired
    private ConfigurableApplicationContext applicationContext;

    @GetMapping("/hello")
    @Override
    public String hello(@RequestParam String name) {
        logger.info("invoked name = " + name);
        return "hello " + name+"port:"+applicationContext.getEnvironment().getProperty("server.port");
    }

    @Override
    public User getUserById(Integer id) {
        return userService.getUser(id);
    }

    @GetMapping
    public String getUserInfo(@RequestParam("userId") String userId){
        return userId;
    }

    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        userService.addUser(user);
        return "success";
    }


}
