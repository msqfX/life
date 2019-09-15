package com.dlion.life.punch.controller;

import com.dlion.life.base.api.ProjectTypeLabelApi;
import com.dlion.life.base.entity.ProjectTypeLabel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.punch.model.ProjectTypeLabelListModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@RestController
@RequestMapping("/api/projectTypeLabel")
public class ProjectTypeLabelController {

    @Autowired
    private ProjectTypeLabelApi projectTypeLabelApi;

    @GetMapping
    public Object listTypeLabel() {

        List<ProjectTypeLabel> parentLabelList = projectTypeLabelApi.listByType(0);

        List<ProjectTypeLabel> childLabelList = projectTypeLabelApi.listByType(1);

        ProjectTypeLabelListModel model = new ProjectTypeLabelListModel();

        model.setParentLabel(parentLabelList);
        model.setChildLabel(childLabelList);

        return new ResponseModel(model);
    }


}
