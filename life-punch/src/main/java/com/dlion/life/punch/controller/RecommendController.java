package com.dlion.life.punch.controller;

import com.dlion.life.base.api.*;
import com.dlion.life.base.entity.*;
import com.dlion.life.common.annotation.LoginUser;
import com.dlion.life.common.bo.DiarySearchPo;
import com.dlion.life.common.constant.CharacterConstant;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.model.PunchCardDiaryRecommendModel;
import com.dlion.life.common.model.RecentThreeAttendUserListModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.common.vo.DiaryResourceVo;
import com.dlion.life.common.vo.ProjectInfo;
import com.dlion.life.common.vo.Publisher;
import com.dlion.life.common.vo.TypeLabel;
import com.dlion.life.punch.model.ProjectListRecommendModel;
import com.dlion.life.punch.service.DiaryResourceService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 系统推荐
 *
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    @Autowired
    private UserApi userApi;

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private DiaryResourceService diaryResourceService;

    @Autowired
    private UserProjectRecordApi userProjectRecordApi;

    /**
     * 打卡系统推荐列表
     *
     * @param dataNum  页数
     * @param nextPage 第几页
     * @return
     */
    @GetMapping("/listProject")
    public Object listProject(Integer dataNum, Integer nextPage) {

        return new ResponseModel();
    }

    /**
     * 首页推荐列表
     *
     * @param loginUser
     * @param pageNo
     * @param dataNum
     * @return
     */
    @GetMapping("/listDiary")
    public Object listDiary(@LoginUser User loginUser, @RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        DiarySearchPo diarySearchPo = new DiarySearchPo(1, 1, 0, null, pageNo, dataNum);
        List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.list(diarySearchPo);

        List<PunchCardDiaryRecommendModel> modelList = punchCardDiaryList.stream().map(punchCardDiary -> {
            PunchCardDiaryRecommendModel model = new PunchCardDiaryRecommendModel();

            BeanUtils.copyProperties(punchCardDiary, model);
            model.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm_ss));

            PunchCardProject punchCardProject = punchCardProjectApi.getById(punchCardDiary.getProjectId());
            ProjectInfo projectInfo = new ProjectInfo();
            BeanUtils.copyProperties(punchCardProject, projectInfo);
            String label = punchCardProject.getTypeLabel();
            String[] typeLabels = label.split(CharacterConstant.COMMA_SPLIT_STR);
            List<TypeLabel> labelList = null;
            if (!ArrayUtils.isEmpty(typeLabels)) {
                labelList = Arrays.stream(typeLabels).map(typeLabelStr -> {

                    String[] labelArr = typeLabelStr.split(CharacterConstant.COMMON_DASH_STR);
                    TypeLabel typeLabel = new TypeLabel();
                    typeLabel.setParentLabel(labelArr[0]);
                    typeLabel.setChildLabel(labelArr[1]);

                    return typeLabel;
                }).collect(Collectors.toList());
            }
            projectInfo.setTypeLabel(labelList);
            model.setProjectInfo(projectInfo);

            Publisher publisher = new Publisher();
            publisher.setId(punchCardDiary.getUserId());
            User user = userApi.getUserById(punchCardDiary.getUserId());
            publisher.setAvatarUrl(user.getAvatarUrl());
            publisher.setGender(user.getGender());
            publisher.setNickName(user.getNickName());
            model.setPublisher(publisher);

            if(Objects.isNull(loginUser)){
                model.setHaveLike(false);
            }else {
                DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(punchCardDiary.getId(), loginUser.getId());
                model.setHaveLike(Objects.nonNull(diaryLike));
                model.setLikeRecordId(Objects.isNull(diaryLike) ? null : diaryLike.getId());
            }

            List<UserProjectRecord> userProjectRecords = userProjectRecordApi.listByProjectId(punchCardDiary.getProjectId());
            List<String> threeAttendList = userProjectRecords.stream().map(userProjectRecord -> {
                User userInfo = userApi.getUserById(userProjectRecord.getUserId());

                return userInfo.getAvatarUrl();
            }).collect(Collectors.toList());
            model.setRecentThreeAttendUserList(threeAttendList);

            //diary_resource
            List<DiaryResourceVo> resourceVoList = diaryResourceService.listByDiaryId(punchCardDiary.getId());
            model.setDiaryResource(resourceVoList);

            return model;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    @GetMapping("/getProjectListByRecommend")
    public Object getProjectListByRecommend(@RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        List<PunchCardProject> punchCardProjectList = punchCardProjectApi.getMorePunchCard(3, pageNo, dataNum);

        List<ProjectListRecommendModel> modelList = punchCardProjectList.stream().map(punchCardProject -> {

            ProjectListRecommendModel projectListRecommendModel = new ProjectListRecommendModel();

            BeanUtils.copyProperties(punchCardProject, projectListRecommendModel);

            return projectListRecommendModel;
        }).collect(Collectors.toList());


        return new ResponseModel(modelList);
    }

    @GetMapping("/search")
    public Object search(@RequestParam String keyword, @RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        List<PunchCardProject> punchCardProjectList = punchCardProjectApi.search(keyword, pageNo, dataNum);

        List<ProjectListRecommendModel> modelList = punchCardProjectList.stream().map(punchCardProject -> {

            ProjectListRecommendModel projectListRecommendModel = new ProjectListRecommendModel();
            //
            BeanUtils.copyProperties(punchCardProject, projectListRecommendModel);

            return projectListRecommendModel;
        }).collect(Collectors.toList());


        return new ResponseModel(modelList);
    }


}
