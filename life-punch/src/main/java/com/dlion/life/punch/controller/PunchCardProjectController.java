package com.dlion.life.punch.controller;

import com.dlion.life.base.api.ProIntrDetailInfoApi;
import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.api.UserProjectRecordApi;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.entity.User;
import com.dlion.life.base.entity.UserProjectRecord;
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

        Map<String, Integer> result = new HashMap<>(1);

        result.put("id", id);

        return new ResponseModel(result);
    }

    @GetMapping("/getProjectInfoByUserId/{userId}")
    public Object listByUserId(@PathVariable Integer userId) {

        User user = userApi.getUserById(userId);

        if (Objects.isNull(user)) {
            return new ResponseModel(ResultConstant.ERROR, "用户信息不存在");
        }

        PunchCardProjectListHomeModel projectHomeModel = new PunchCardProjectListHomeModel();
        projectHomeModel.setId(userId);
        projectHomeModel.setGender(user.getGender());
        projectHomeModel.setNickName(user.getNickName());

        List<PunchCardProject> projectList = punchCardProjectApi.getByUserId(userId);

        List<PunchCardProjectListModel> modelList = projectList.stream().map(project -> {

            PunchCardProjectListModel model = new PunchCardProjectListModel();

            BeanUtils.copyProperties(project, model);

            if (Objects.equals(project.getCreatorId(), userId)) {
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
    public Object getProjectInfoById(@PathVariable Integer id, @RequestParam String userId) {

        PunchCardProject cardProject = punchCardProjectApi.getById(id);

        if (Objects.isNull(cardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
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

    @GetMapping("/checkUserIsAttend")
    public Object checkUserIsAttend(@RequestParam Integer userId, @RequestParam Integer projectId) {

        Map<String, Boolean> result = new HashMap<>();

        UserProjectRecord projectRecord = userProjectRecordApi.getByUserId(userId, projectId);
        if (Objects.isNull(projectRecord)) {
            result.put("checkUserIsAttendRes", false);
        } else {
            result.put("checkUserIsAttendRes", true);
        }

        return new ResponseModel(result);
    }

    /**
     * 加入圈子
     *
     * @param joinProjectModel
     * @return
     */
    @PutMapping("/joinProject")
    public Object joinProject(@RequestBody JoinProjectModel joinProjectModel) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(joinProjectModel.getProjectId());

        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        UserProjectRecord userProjectRecord = new UserProjectRecord();
        userProjectRecord.setProjectId(joinProjectModel.getProjectId());
        if (Objects.equals(punchCardProject.getCreatorId(), joinProjectModel.getUserId())) {
            userProjectRecord.setIsCreator(1);
        } else {
            userProjectRecord.setIsCreator(0);
        }

        userProjectRecord.setUserId(joinProjectModel.getUserId());
        userProjectRecord.setAttendTime(new Date());
        userProjectRecord.setAttendStatus(1);

        userProjectRecordApi.add(userProjectRecord);

        //update attend_num
        PunchCardProject punchProject = new PunchCardProject();
        punchProject.setId(joinProjectModel.getProjectId());
        punchProject.setAttendUserNum(punchCardProject.getAttendUserNum() + 1);
        punchCardProjectApi.update(punchProject);

        return new ResponseModel();
    }


    @GetMapping("/getProjectListByType")
    public Object getProjectListByType(@RequestParam String typeName, @RequestParam Integer pageNo,
                                       @RequestParam Integer pageSize) {

        List<PunchCardProject> punchCardProjects = punchCardProjectApi.getProjectListByType(typeName, pageNo, pageSize);

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
        newPunchCardProject.setCoverImgUrl(updateCoverImg.getCurCoverImgUrl());

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
