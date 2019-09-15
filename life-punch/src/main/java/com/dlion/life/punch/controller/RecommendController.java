package com.dlion.life.punch.controller;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.CharacterConstant;
import com.dlion.life.common.model.PunchCardDiaryRecommendModel;
import com.dlion.life.common.model.RecentThreeAttendUserListModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.vo.ProjectInfo;
import com.dlion.life.common.vo.Publisher;
import com.dlion.life.common.vo.TypeLabel;
import com.dlion.life.punch.model.ProjectListRecommendModel;
import com.dlion.life.punch.service.PunchCardDiaryService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
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

    @GetMapping("/listDiary")
    public Object listDiary(@RequestParam Integer userId, @RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        List<PunchCardProject> punchCardProjectList = punchCardProjectApi.getMorePunchCard(3, pageNo, dataNum);

        List<PunchCardDiaryRecommendModel> modelList = punchCardProjectList.stream().map(punchCardProject -> {

            PunchCardDiaryRecommendModel model = new PunchCardDiaryRecommendModel();

            List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.getByProjectId(punchCardProject.getId());

            //默认取punch_card_diary表的第一个
            if (!CollectionUtils.isEmpty(punchCardDiaryList)) {
                PunchCardDiary punchCardDiary = punchCardDiaryList.get(0);
                model.setId(punchCardDiary.getId());
                model.setTextContent(punchCardDiary.getTextContent());
                model.setLikeUserNum(punchCardDiary.getLikeUserNum());

                model.setPunchCardTime(punchCardDiary.getPunchCardTime());
                model.setAddressLatitude(punchCardDiary.getAddressLatitude());
                model.setAddressLongitude(punchCardDiary.getAddressLongitude());
                model.setCommentNum(punchCardDiary.getCommentNum());
                model.setPunchCardAddress(punchCardDiary.getPunchCardAddress());

                ProjectInfo projectInfo = new ProjectInfo();
                projectInfo.setId(punchCardProject.getId());
                projectInfo.setCoverImgUrl(punchCardProject.getCoverImgUrl());
                projectInfo.setProjectName(punchCardProject.getProjectName());

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

                DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(punchCardDiary.getId(), userId);
                model.setHaveLike(Objects.nonNull(diaryLike));
                model.setLikeRecordId(Objects.isNull(diaryLike) ? null : diaryLike.getId());

                RecentThreeAttendUserListModel threeAttendUserListModel = new RecentThreeAttendUserListModel();
                threeAttendUserListModel.setAvatarUrl(user.getAvatarUrl());
                List<RecentThreeAttendUserListModel> threeAttendList = new ArrayList<>();
                threeAttendList.add(threeAttendUserListModel);
                model.setRecentThreeAttendUserList(threeAttendList);

            }

            return model;
        }).collect(Collectors.toList());

        Iterator<PunchCardDiaryRecommendModel> iterator = modelList.iterator();
        while (iterator.hasNext()) {
            if (Objects.isNull(iterator.next().getId())) {
                iterator.remove();
            }
        }

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
