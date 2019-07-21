package com.dlion.life.user.controller;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.ChannelConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.model.UserModel;
import com.dlion.life.user.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author 李正元
 * @date 2019-07-21
 */

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserApi userApi;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id) {

        User user = userApi.getUserById(id);

        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(user, userModel);

        return new ResponseModel(userModel);

    }

    @GetMapping
    public Object getByOpenId(@RequestParam String openId) {

        User user = userApi.getByOpenId(openId);

        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(user, userModel);

        return new ResponseModel(userModel);

    }

    @PostMapping
    public Object addUser(@RequestBody UserModel userModel) {

        User user = new User();

        BeanUtils.copyProperties(userModel, user);

        user.setChannel(ChannelConstant.WEIXIN_MINIPROGRAM);

        userApi.addUser(user);

        return new ResponseModel();

    }

    @PutMapping("{id}")
    public Object update(@PathVariable Integer id, @RequestBody UserModel userModel) {

        User user = new User();

        BeanUtils.copyProperties(userModel, user);

        user.setChannel(ChannelConstant.WEIXIN_MINIPROGRAM);

        return new ResponseModel();

    }

    /**
     * 发送 res.code 到后台换取 openId, sessionKey, unionId
     *
     * @param code
     * @return
     */
    @GetMapping("/getWxUserInfo")
    public Object getWxUserInfo(@RequestParam String code) {

        Map<String, Object> wxUserInfo = userService.getWxUserInfo(code);

        return new ResponseModel(wxUserInfo);
    }


}
