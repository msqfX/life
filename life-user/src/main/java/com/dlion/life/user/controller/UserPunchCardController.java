package com.dlion.life.user.controller;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserProjectRecordApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.entity.UserProjectRecord;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.user.model.UserPagePunchCardListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/28
 */
@RestController
@RequestMapping("/api/userPunchCard")
public class UserPunchCardController {

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private UserProjectRecordApi userProjectRecordApi;

    /**
     * 获取用户参与的打卡圈子
     * 自己查看自己的个人主页后端返回全部的打卡圈子列表
     * 其他用户查看则后端只返回公开类型的打卡圈子列表
     *
     * @param userId
     * @param isDiaryCreator 代表查询自己的打卡圈子列表 0则代表查看他人的
     * @return
     */
    @GetMapping("/getUserPunchCardProjectListByType")
    public Object getUserPunchCardProjectListByType(@RequestParam Integer userId, @RequestParam Integer isDiaryCreator) {

        List<UserProjectRecord> userProjectRecords = userProjectRecordApi.listByUserId(userId);
        List<UserPagePunchCardListModel> pagePunchCardListModels = userProjectRecords.stream().map(userProjectRecord -> {
            UserPagePunchCardListModel userPagePunchCardListModel = new UserPagePunchCardListModel();
            PunchCardProject project = punchCardProjectApi.getById(userProjectRecord.getProjectId());
            userPagePunchCardListModel.setId(userProjectRecord.getProjectId());
            userPagePunchCardListModel.setProjectName(project.getProjectName());

            //所在圈子打卡天数、排名
            userPagePunchCardListModel.setCurrDiaryPunchCardDayNum(userProjectRecord.getAllPunchCardNum());

            return userPagePunchCardListModel;
        }).collect(Collectors.toList());

        return new ResponseModel(pagePunchCardListModels);
    }

}
