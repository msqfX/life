package com.dlion.life.mgt.controller;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.model.ResponseModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019/9/10
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserApi userApi;

    @GetMapping("/getUser")
    public Object getUser() {

        User user = userApi.getUserById(1);
        return new ResponseModel(user);
    }

}
