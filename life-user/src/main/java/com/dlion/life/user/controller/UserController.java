package com.dlion.life.user.controller;

import com.dlion.life.base.api.PersonalPageVisitRecordApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.PersonalPageVisitRecord;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.ChannelConstant;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.model.UserModel;
import com.dlion.life.user.model.PersonalPageVisitRecordModel;
import com.dlion.life.user.model.UserHomePageModel;
import com.dlion.life.user.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

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
    private PersonalPageVisitRecordApi personalPageVisitRecordApi;

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

        if (Objects.isNull(user)) {
            return new ResponseModel();
        }

        UserModel userModel = new UserModel();

        BeanUtils.copyProperties(user, userModel);

        return new ResponseModel(userModel);

    }

    @PostMapping
    public Object addUser(@RequestBody UserModel userModel) {

        String openId = userModel.getOpenId();

        //目前默认渠道为微信小程序
        userModel.setChannel(ChannelConstant.WEIXIN_MINIPROGRAM);

        if (ChannelConstant.WEIXIN_MINIPROGRAM.equals(userModel.getChannel())) {
            if (StringUtils.isEmpty(openId)) {
                return new ResponseModel(ResultConstant.ERROR, "小程序渠道openId不能为空");
            }
        }

        User temUser = new User();
        BeanUtils.copyProperties(userModel, temUser);

        User dbUser = userApi.getByOpenId(userModel.getOpenId());
        if (Objects.nonNull(dbUser)) {

            temUser.setId(dbUser.getId());

            userApi.updateUser(temUser);
            userModel.setId(dbUser.getId());
        } else {
            Integer userId = userApi.addUser(temUser);
            userModel.setId(userId);
        }

        return new ResponseModel(userModel);
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


    @GetMapping("/getUserDetailInfoById")
    public Object getUserDetailInfoById(@RequestParam Integer visitedId, @RequestParam Integer visitorId) {

        User user = userApi.getUserById(visitedId);

        UserHomePageModel homePageModel = new UserHomePageModel();

        BeanUtils.copyProperties(user, homePageModel);

        List<PersonalPageVisitRecord> personalPageVisitRecords = personalPageVisitRecordApi.getByUserId(visitedId);

        List<PersonalPageVisitRecordModel> personalPageVisitRecordModels = personalPageVisitRecords.stream().map(personalPageVisitRecord -> {
            PersonalPageVisitRecordModel pageVisitRecordModel = new PersonalPageVisitRecordModel();
            User visitorInfo = userApi.getUserById(personalPageVisitRecord.getVisitorId());
            BeanUtils.copyProperties(visitorInfo, user);
            return pageVisitRecordModel;
        }).collect(Collectors.toList());

        homePageModel.setFivePersonalPageVisitRecord(personalPageVisitRecordModels);

        return new ResponseModel(homePageModel);
    }


}
