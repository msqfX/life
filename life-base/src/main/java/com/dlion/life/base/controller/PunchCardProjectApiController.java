package com.dlion.life.base.controller;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.service.PunchCardProjectService;
import com.dlion.life.common.bo.ProjectSearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
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
    public Integer add(@RequestBody PunchCardProject punchCardProject) {

        return punchCardProjectService.addPunchCardProject(punchCardProject);
    }

    @Override
    public void update(@RequestBody PunchCardProject punchCardProject) {

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

    @Override
    public List<PunchCardProject> getMorePunchCard(Integer num, Integer pageNo, Integer dataNum) {

        return punchCardProjectService.getMorePunchCard(num, pageNo, dataNum);
    }

    @Override
    public List<PunchCardProject> search(String keyword, Integer pageNo, Integer dataNum) {

        return punchCardProjectService.search(keyword, pageNo, dataNum);
    }

    @Override
    public List<PunchCardProject> getProjectListByType(@RequestBody ProjectSearchPo projectSearchPo) {

        return punchCardProjectService.getProjectListByType(projectSearchPo);
    }

    @Override
    public void refreshTodayPunchCardNum() {

        punchCardProjectService.refreshTodayPunchCardNum();
    }
}
