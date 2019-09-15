package com.dlion.life.base.controller;

import com.dlion.life.base.api.ProIntrDetailInfoApi;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.base.service.ProIntrDetailInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/14
 */
@RestController
public class ProIntrDetailInfoApiController implements ProIntrDetailInfoApi {

    @Autowired
    private ProIntrDetailInfoService proIntrDetailInfoService;

    @Override
    public List<ProIntrDetailInfo> getByProjectId(Integer projectId) {

        return proIntrDetailInfoService.getByProjectId(projectId);
    }

    @Override
    public void add(@RequestBody ProIntrDetailInfo proIntrDetailInfo) {

        proIntrDetailInfoService.add(proIntrDetailInfo);
    }

    @Override
    public void update(ProIntrDetailInfo proIntrDetailInfo) {

        proIntrDetailInfoService.update(proIntrDetailInfo);
    }

    @Override
    public void delete(Integer id) {

        proIntrDetailInfoService.delete(id);
    }
}
