package com.dlion.life.base.controller;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.service.PunchCardProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
public class PunchCardProjectApiController implements PunchCardProjectApi {

    @Autowired
    private PunchCardProjectService punchCardProjectService;

    @Override
    public void add(PunchCardProject punchCardProject) {

        punchCardProjectService.addPunchCardProject(punchCardProject);
    }

    @Override
    public void update(PunchCardProject punchCardProject) {

        punchCardProjectService.updatePunchCardProject(punchCardProject);
    }

    @Override
    public void deleteById(Integer id) {

        punchCardProjectService.delete(id);
    }

    @Override
    public List<PunchCardProject> getByUserId(Integer userId) {

        return punchCardProjectService.getByUserId(userId);
    }

    @Override
    public PunchCardProject getById(Integer id) {

        return punchCardProjectService.getById(id);
    }
}
