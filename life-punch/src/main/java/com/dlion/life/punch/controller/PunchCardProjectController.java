package com.dlion.life.punch.controller;

import com.dlion.life.base.api.ProIntrDetailInfoApi;
import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.api.UserProjectRecordApi;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.entity.User;
import com.dlion.life.base.entity.UserProjectRecord;
import com.dlion.life.common.annotation.Login;
import com.dlion.life.common.annotation.LoginUser;
import com.dlion.life.common.bo.ProjectSearchPo;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.PunchCardProjectListHomeModel;
import com.dlion.life.common.model.PunchCardProjectListModel;
import com.dlion.life.common.model.PunchCardProjectModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.punch.model.*;
import com.dlion.life.punch.service.FileService;
import com.dlion.life.punch.vo.PivotVo;
import com.dlion.life.punch.vo.UpdateCoverImgVo;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/punchCardProject")
public class PunchCardProjectController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private UserApi userApi;

    @Autowired
    private ProIntrDetailInfoApi proIntrDetailInfoApi;

    @Autowired
    private UserProjectRecordApi userProjectRecordApi;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Object add(@RequestBody PunchCardProjectModel punchCardProjectModel) {

        PunchCardProject punchCardProject = new PunchCardProject();

        BeanUtils.copyProperties(punchCardProjectModel, punchCardProject);

        Integer id = punchCardProjectApi.add(punchCardProject);

        // 将自己添加到记录中
        UserProjectRecord userProjectRecord = new UserProjectRecord();
        userProjectRecord.setProjectId(id);
        userProjectRecord.setUserId(punchCardProjectModel.getCreatorId());
        userProjectRecord.setIsCreator(1);
        userProjectRecord.setAttendStatus(1);
        userProjectRecord.setAttendTime(new Date());
        userProjectRecordApi.add(userProjectRecord);

        Map<String, Integer> result = new HashMap<>(1);
        result.put("id", id);

        return new ResponseModel(result);
    }

    @GetMapping("/getUserProjectListInfo")
    public Object listByUserId(@LoginUser User loginUser) {

        PunchCardProjectListHomeModel projectHomeModel = new PunchCardProjectListHomeModel();
        if (Objects.isNull(loginUser)) {
            projectHomeModel.setPunchCardProjectList(new ArrayList<>());
            return new ResponseModel(projectHomeModel);
        }

        if (Objects.nonNull(loginUser)) {
            projectHomeModel.setId(loginUser.getId());
            projectHomeModel.setGender(loginUser.getGender());
            projectHomeModel.setNickName(loginUser.getNickName());
        }

        List<PunchCardProject> projectList = punchCardProjectApi.getByUserId(loginUser.getId());

        List<PunchCardProjectListModel> modelList = projectList.stream().map(project -> {

            PunchCardProjectListModel model = new PunchCardProjectListModel();

            BeanUtils.copyProperties(project, model);

            if (Objects.equals(project.getCreatorId(), loginUser.getId())) {
                model.setIsCreator(1);
            } else {
                model.setIsCreator(0);
            }

            return model;
        }).collect(Collectors.toList());

        projectHomeModel.setPunchCardProjectList(modelList);

        return new ResponseModel(projectHomeModel);
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Integer id) {

        punchCardProjectApi.deleteById(id);

        return new ResponseModel();
    }

    @PutMapping("{id}")
    public Object update(@PathVariable Integer id, @RequestBody PunchCardProjectModel punchCardProjectModel) {

        PunchCardProject temProject = punchCardProjectApi.getById(id);

        if (Objects.isNull(temProject)) {
            return new ResponseModel("记录不存在");
        }

        PunchCardProject punchCardProject = new PunchCardProject();

        BeanUtils.copyProperties(punchCardProjectModel, punchCardProject);
        punchCardProject.setId(id);

        punchCardProjectApi.update(punchCardProject);

        return new ResponseModel();
    }

    @PutMapping("/{projectId}/updateName")
    public Object updateName(@PathVariable Integer projectId, @RequestBody PunchCardProjectModel punchCardProjectModel) {

        String projectName = punchCardProjectModel.getProjectName();

        if (StringUtils.isEmpty(projectName)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子名称不能为空");
        }

        PunchCardProject punchCardProject = punchCardProjectApi.getById(projectId);

        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        punchCardProject = new PunchCardProject();

        punchCardProject.setId(projectId);
        punchCardProject.setProjectName(projectName);

        punchCardProjectApi.update(punchCardProject);

        return new ResponseModel();
    }

    /**
     * 获取圈子详情
     *
     * @param id 圈子id
     * @return
     */
    @GetMapping("{id}")
    public Object getProjectInfoById(@LoginUser User loginUser, @PathVariable Integer id) {

        PunchCardProject cardProject = punchCardProjectApi.getById(id);

        if (Objects.isNull(cardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        // 隐私圈子不允许别人访问
        if (Objects.equals(cardProject.getPrivacyType(), 1) && (Objects.isNull(loginUser) || !Objects.equals(cardProject.getCreatorId(), loginUser.getId()))) {
            return new ResponseModel(ResultConstant.ERROR, "隐私圈子只有主人才可以访问");
        }

        ProjectInfoModel projectInfoModel = new ProjectInfoModel();

        User user = userApi.getUserById(cardProject.getCreatorId());
        if (Objects.nonNull(user)) {

            BeanUtils.copyProperties(cardProject, projectInfoModel);

            projectInfoModel.setCreatorGender(user.getGender());
            projectInfoModel.setCreatorAvatarUrl(user.getAvatarUrl());
            projectInfoModel.setCreatorNickName(user.getNickName());


            List<ProIntrDetailInfo> proIntrDetailInfoList = proIntrDetailInfoApi.getByProjectId(id);
            List<ProIntrDetailInfoModel> proIntrDetailInfoModels = proIntrDetailInfoList.stream().map(proIntrDetailInfo -> {

                ProIntrDetailInfoModel proIntrDetailInfoModel = new ProIntrDetailInfoModel();

                BeanUtils.copyProperties(proIntrDetailInfo, proIntrDetailInfoModel);
                proIntrDetailInfoModel.setProjectId(proIntrDetailInfo.getProjectId());

                return proIntrDetailInfoModel;
            }).collect(Collectors.toList());

            projectInfoModel.setProjectIntrInfo(proIntrDetailInfoModels);

            List<UserProjectRecord> projectRecordList = userProjectRecordApi.listByProjectId(id);

            List<AttendUserModel> attendUserModelList = projectRecordList.stream().map(userProjectRecord -> {

                AttendUserModel attendUserModel = new AttendUserModel();

                User userInfo = userApi.getUserById(userProjectRecord.getUserId());
                attendUserModel.setAvatarUrl(userInfo.getAvatarUrl());
                attendUserModel.setId(userInfo.getId());
                attendUserModel.setNickName(userInfo.getNickName());
                attendUserModel.setGender(userInfo.getGender());

                PivotVo pivotVo = new PivotVo();
                pivotVo.setId(userProjectRecord.getId());
                val attendTime = DateUtil.formatDate(userProjectRecord.getAttendTime(), DatePattern.YYYY_MM_DD);
                pivotVo.setAttendTime(attendTime);

                attendUserModel.setPivot(pivotVo);

                return attendUserModel;
            }).collect(Collectors.toList());

            projectInfoModel.setAttendUserList(attendUserModelList);
        }

        return new ResponseModel(projectInfoModel);
    }

    /**
     * 查询用户是否参与当前圈子
     *
     * @param loginUser 当前登录用户
     * @param projectId 圈子ID
     * @return
     */
    @GetMapping("/checkUserIsAttend")
    public Object checkUserIsAttend(@LoginUser User loginUser, @RequestParam Integer projectId) {

        Map<String, Boolean> result = new HashMap<>();
        result.put("checkUserIsAttendRes", false);

        if (Objects.isNull(loginUser)) {
            return new ResponseModel(result);
        }

        UserProjectRecord projectRecord = userProjectRecordApi.getByUserId(loginUser.getId(), projectId);
        if (Objects.nonNull(projectRecord)) {
            result.put("checkUserIsAttendRes", true);
        }

        return new ResponseModel(result);
    }

    /**
     * 加入圈子
     *
     * @param loginUser        当前登录用户
     * @param joinProjectModel 圈子记录对象
     * @return
     */
    @PostMapping("/joinProject")
    @Login
    public Object joinProject(@LoginUser User loginUser, @RequestBody JoinProjectModel joinProjectModel) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(joinProjectModel.getProjectId());

        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        UserProjectRecord dbRecord = userProjectRecordApi.getByUserId(loginUser.getId(), joinProjectModel.getProjectId());
        if (Objects.nonNull(dbRecord)) {
            return new ResponseModel(ResultConstant.ERROR, "已加入到圈子");
        }

        UserProjectRecord userProjectRecord = new UserProjectRecord();
        userProjectRecord.setProjectId(joinProjectModel.getProjectId());
        if (Objects.equals(punchCardProject.getCreatorId(), loginUser.getId())) {
            userProjectRecord.setIsCreator(1);
        } else {
            userProjectRecord.setIsCreator(0);
        }

        userProjectRecord.setUserId(loginUser.getId());
        userProjectRecord.setAttendTime(new Date());
        userProjectRecord.setAttendStatus(1);

        userProjectRecordApi.add(userProjectRecord);

        //update attend_num
        PunchCardProject punchProject = new PunchCardProject();
        punchProject.setId(joinProjectModel.getProjectId());
        if (!Objects.equals(punchCardProject.getCreatorId(), loginUser.getId())) {
            punchProject.setAttendUserNum(punchCardProject.getAttendUserNum() + 1);
            punchCardProjectApi.update(punchProject);
        }

        return new ResponseModel();
    }


    @GetMapping("/getProjectListByType")
    public Object getProjectListByType(ProjectSearchPo projectSearchPo) {

        projectSearchPo.setPrivacyType(0);
        List<PunchCardProject> punchCardProjects = punchCardProjectApi.getProjectListByType(projectSearchPo);

        List<SearchPunchCardProjectModel> modelList = punchCardProjects.stream().map(punchCardProject -> {

            SearchPunchCardProjectModel model = new SearchPunchCardProjectModel();

            BeanUtils.copyProperties(punchCardProject, model);

            return model;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    /**
     * 获取创建者信息
     *
     * @param projectId 圈子ID
     * @return
     */
    @GetMapping("/getCreatorInfo")
    public Object getCreatorInfo(@RequestParam Integer projectId) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(projectId);
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        CreatorInfoModel model = new CreatorInfoModel();
        model.setCreatorIntroduce(punchCardProject.getCreatorIntroduce());
        model.setWeixinNum(punchCardProject.getWeixinNum());

        return new ResponseModel(model);
    }

    /**
     * 更新圈子背景图片
     *
     * @param updateCoverImg
     * @return
     */
    @PutMapping("/updateCoverImg")
    public Object updateCoverImg(@RequestBody UpdateCoverImgVo updateCoverImg) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(updateCoverImg.getProjectId());
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        PunchCardProject newPunchCardProject = new PunchCardProject();
        newPunchCardProject.setId(updateCoverImg.getProjectId());
        newPunchCardProject.setCoverImgUrl(updateCoverImg.getNewImgUrl());

        punchCardProjectApi.update(newPunchCardProject);

        return new ResponseModel();
    }

    /**
     * 上传本地图片
     *
     * @return
     */
    @PostMapping(value = "/uploadCoverImg", consumes = "multipart/form-data")
    public Object uploadCoverImg(HttpServletRequest request, @RequestParam("image") MultipartFile multipartFile) {

        String preSysRecommendCoverImgId = request.getParameter("preSysRecommendCoverImgId");
        String curCoverImgUrl = request.getParameter("curCoverImgUrl");
        String projectId = request.getParameter("projectId");

        if (StringUtils.isEmpty(projectId)) {
            return new ResponseModel(ResultConstant.ERROR, "参数异常");
        }

        PunchCardProject punchCardProject = punchCardProjectApi.getById(Integer.valueOf(projectId));
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        Map<String, Object> result = new HashMap<>(1);

        if (!multipartFile.isEmpty()) {
            try {
                String fileUrl = fileService.uploadFile(multipartFile.getInputStream(), CommonUtil.getFileName());
                result.put("coverImgUrl", fileUrl);

                PunchCardProject newPunchCardProject = new PunchCardProject();
                newPunchCardProject.setId(Integer.valueOf(projectId));
                newPunchCardProject.setCoverImgUrl(fileUrl);

                punchCardProjectApi.update(newPunchCardProject);

            } catch (IOException e) {
                logger.error("上传圈子背景图片异常，参数:{}", request.getParameterMap(), e);
            }
        }

        return new ResponseModel(result);
    }

}
