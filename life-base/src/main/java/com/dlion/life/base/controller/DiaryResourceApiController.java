package com.dlion.life.base.controller;

import com.dlion.life.base.api.DiaryResourceApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.DiaryResource;
import com.dlion.life.base.service.DiaryResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@RestController
public class DiaryResourceApiController implements DiaryResourceApi {

    @Autowired
    private DiaryResourceService diaryResourceService;

    @Override
    public DiaryResource getById(Integer id) {

        return diaryResourceService.getById(id);
    }

    @Override
    public void add(DiaryResource diaryResource) {

        diaryResourceService.add(diaryResource);
    }

    @Override
    public void delete(Integer id) {

        diaryResourceService.delete(id);
    }

    @Override
    public List<DiaryResource> getByDiaryId(Integer diaryId) {

        return diaryResourceService.getByDiaryId(diaryId);
    }
}
