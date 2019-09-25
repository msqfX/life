package com.dlion.life.base.controller;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.base.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@RestController
public class UserApiController implements UserApi {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    public User getUserById(Integer id) {

        return userService.getUser(id);

    }

    @Override
    public User getByOpenId(String openId) {

        return userService.getByOpenId(openId);

    }

    @Override
    public Integer addUser(@RequestBody User user) {

        return userService.addUser(user);
    }

    @Override
    public void updateUser(@RequestBody User user) {

        userService.updateUser(user);
    }

    @Override
    public List<User> list(String userName, Integer pageNum, Integer pageSize) {

        return userService.list(userName, pageNum, pageSize);
    }


}
