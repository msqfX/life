package com.dlion.life.punch.controller;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.PunchCardProjectListHomeModel;
import com.dlion.life.common.model.PunchCardProjectListModel;
import com.dlion.life.common.model.PunchCardProjectModel;
import com.dlion.life.common.model.ResponseModel;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/punchCardProject")
public class PunchCardProjectController {

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private UserApi userApi;

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
    public Object getProjectInfoById(@PathVariable Integer id) {


        return new ResponseModel();
    }


}
