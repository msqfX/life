package com.dlion.life.base.service;

import com.dlion.life.base.dao.DiaryResourceMapper;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.DiaryResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Service
public class DiaryResourceService {

    @Autowired
    private DiaryResourceMapper diaryResourceMapper;

    public void add(DiaryResource diaryResource){

        diaryResourceMapper.insertSelective(diaryResource);
    }

    public void delete(Integer id){

        diaryResourceMapper.deleteByPrimaryKey(id);
    }

    public DiaryResource getById(Integer id){

        return diaryResourceMapper.selectByPrimaryKey(id);
    }

    public List<DiaryResource> getByDiaryId(Integer diaryId) {

        return diaryResourceMapper.getByDiaryId(diaryId);
    }

    public void deleteByDiaryId(Integer diaryId) {

        diaryResourceMapper.deleteByDiaryId(diaryId);
    }
}
