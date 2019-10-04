package com.dlion.life.base.controller;

import com.dlion.life.base.api.UserProjectRecordApi;
import com.dlion.life.base.entity.UserProjectRecord;
import com.dlion.life.base.service.UserProjectRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@RestController
public class UserProjectRecordApiController implements UserProjectRecordApi {

    @Autowired
    private UserProjectRecordService userProjectRecordService;

    @Override
    public List<UserProjectRecord> listByProjectId(Integer projectId) {

        return userProjectRecordService.listByProjectId(projectId);
    }

    @Override
    public UserProjectRecord getByUserId(Integer userId, Integer projectId) {

        return userProjectRecordService.getByUserId(userId, projectId);
    }

    @Override
    public void add(@RequestBody UserProjectRecord userProjectRecord) {

        userProjectRecordService.add(userProjectRecord);
    }

    @Override
    public List<UserProjectRecord> listByUserId(Integer userId) {

        return userProjectRecordService.listByUserId(userId);
    }

    @Override
    public void update(UserProjectRecord newUserProjectRecord) {

        userProjectRecordService.update(newUserProjectRecord);
    }
}
