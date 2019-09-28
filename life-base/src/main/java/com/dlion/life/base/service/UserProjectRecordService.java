package com.dlion.life.base.service;

import com.dlion.life.base.dao.UserProjectRecordMapper;
import com.dlion.life.base.entity.UserProjectRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-09-20
 */
@Service
public class UserProjectRecordService {

    @Autowired
    private UserProjectRecordMapper projectRecordMapper;

    public List<UserProjectRecord> listByProjectId(Integer projectId) {

        return projectRecordMapper.selectByProjectId(projectId);
    }

    public UserProjectRecord getByUserId(Integer userId, Integer projectId) {

        return projectRecordMapper.selectByUserId(userId, projectId);
    }

    public void add(UserProjectRecord userProjectRecord) {

        projectRecordMapper.insertSelective(userProjectRecord);
    }

    public List<UserProjectRecord> listByUserId(Integer userId) {

        return projectRecordMapper.listByUserId(userId);
    }
}
