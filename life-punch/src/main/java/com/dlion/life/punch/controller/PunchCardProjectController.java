package com.dlion.life.punch.controller;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.model.PunchCardProjectModel;
import com.dlion.life.common.model.ResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/PunchCardProject")
public class PunchCardProjectController {

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @PostMapping
    public Object add(@RequestBody PunchCardProjectModel punchCardProjectModel) {

        PunchCardProject punchCardProject = new PunchCardProject();

        BeanUtils.copyProperties(punchCardProjectModel, punchCardProject);

        return new ResponseModel();
    }

    @GetMapping("{userId}")
    public Object listByUserId(@PathVariable Integer userId) {

        List<PunchCardProject> projectList = punchCardProjectApi.getByUserId(userId);

        List<PunchCardProjectModel> modelList = projectList.stream().map(project -> {

            PunchCardProjectModel model = new PunchCardProjectModel();

            BeanUtils.copyProperties(project, model);
            return model;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
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

}
