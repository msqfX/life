package com.dlion.life.base.service;

import com.dlion.life.base.dao.PunchCardProjectMapper;
import com.dlion.life.base.entity.PunchCardProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@Service
public class PunchCardProjectService {

    @Autowired
    private PunchCardProjectMapper punchCardProjectMapper;

    public void addPunchCardProject(PunchCardProject punchCardProject) {

        punchCardProjectMapper.insertSelective(punchCardProject);
    }

    public void updatePunchCardProject(PunchCardProject punchCardProject) {

        punchCardProjectMapper.updateByPrimaryKeySelective(punchCardProject);
    }

    public void delete(Integer id) {

        punchCardProjectMapper.deleteByPrimaryKey(id);
    }

    public List<PunchCardProject> getByUserId(Integer userId) {

        return punchCardProjectMapper.selectByUserId(userId);
    }


    public PunchCardProject getById(Integer id) {

        return punchCardProjectMapper.selectByPrimaryKey(id);
    }

    public List<PunchCardProject> getMorePunchCard(Integer num, Integer pageNo, Integer dataNum) {

        return punchCardProjectMapper.getMorePunchCard(num, pageNo, dataNum);
    }

    public List<PunchCardProject> search(String keyword, Integer pageNo, Integer dataNum) {

        return punchCardProjectMapper.search(keyword, pageNo, dataNum);
    }
}
