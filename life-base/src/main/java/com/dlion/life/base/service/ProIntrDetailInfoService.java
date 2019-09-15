package com.dlion.life.base.service;

import com.dlion.life.base.dao.ProIntrDetailInfoMapper;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/14
 */
@Service
public class ProIntrDetailInfoService {

    @Autowired
    private ProIntrDetailInfoMapper proIntrDetailInfoMapper;

    public List<ProIntrDetailInfo> getByProjectId(Integer projectId) {

        return proIntrDetailInfoMapper.selectByProjectId(projectId);
    }

    public void update(ProIntrDetailInfo proIntrDetailInfo) {

        proIntrDetailInfoMapper.updateByPrimaryKeySelective(proIntrDetailInfo);
    }

    public void add(ProIntrDetailInfo proIntrDetailInfo) {

        proIntrDetailInfoMapper.insertSelective(proIntrDetailInfo);
    }

    public void delete(Integer id) {

        proIntrDetailInfoMapper.deleteByPrimaryKey(id);
    }
}
