package com.dlion.life.base.service;

import com.dlion.life.base.dao.ProjectTypeLabelMapper;
import com.dlion.life.base.entity.ProjectTypeLabel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@Service
public class ProjectTypeLabelService {

    @Autowired
    private ProjectTypeLabelMapper projectTypeLabelMapper;

    public void add(ProjectTypeLabel projectTypeLabel) {

        projectTypeLabelMapper.insertSelective(projectTypeLabel);
    }

    public void update(ProjectTypeLabel projectTypeLabel) {

        projectTypeLabelMapper.updateByPrimaryKeySelective(projectTypeLabel);
    }

    public ProjectTypeLabel getById(Integer id) {

        return projectTypeLabelMapper.selectByPrimaryKey(id);
    }

    public void delete(Integer id) {

        projectTypeLabelMapper.deleteByPrimaryKey(id);
    }


}
