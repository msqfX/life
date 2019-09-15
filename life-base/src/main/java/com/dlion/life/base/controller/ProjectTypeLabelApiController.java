package com.dlion.life.base.controller;

import com.dlion.life.base.api.ProjectTypeLabelApi;
import com.dlion.life.base.entity.ProjectTypeLabel;
import com.dlion.life.base.service.ProjectTypeLabelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@RestController
public class ProjectTypeLabelApiController implements ProjectTypeLabelApi {

    @Autowired
    private ProjectTypeLabelService projectTypeLabelService;

    @Override
    public void add(@RequestBody ProjectTypeLabel projectTypeLabel) {

        projectTypeLabelService.add(projectTypeLabel);
    }

    @Override
    public void update(@RequestBody ProjectTypeLabel projectTypeLabel) {

        projectTypeLabelService.update(projectTypeLabel);
    }

    @Override
    public void delete(Integer id) {

        projectTypeLabelService.delete(id);
    }

    @Override
    public ProjectTypeLabel getById(Integer id) {

        return projectTypeLabelService.getById(id);
    }
}
