package com.dlion.life.punch.service;

import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.vo.PunchCardProjectVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Service
public class PunchCardProjectService {

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private UserApi userApi;

    public PunchCardProjectVo punchCardProject(Integer projectId) {

        if (Objects.isNull(projectId)) {
            return null;
        }

        PunchCardProjectVo punchCardProjectVo = new PunchCardProjectVo();

        PunchCardProject punchCardProject = punchCardProjectApi.getById(projectId);

        BeanUtils.copyProperties(punchCardProject, punchCardProjectVo);

        return punchCardProjectVo;
    }

}
