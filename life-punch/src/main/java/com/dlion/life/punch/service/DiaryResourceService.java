package com.dlion.life.punch.service;

import com.dlion.life.base.api.DiaryResourceApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.DiaryResource;
import com.dlion.life.common.vo.DiaryResourceVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Service
public class DiaryResourceService {

    @Autowired
    private DiaryResourceApi diaryResourceApi;

    public List<DiaryResourceVo> listByDiaryId(Integer diaryId) {

        List<DiaryResource> diaryResourceList = diaryResourceApi.getByDiaryId(diaryId);

        List<DiaryResourceVo> voList = diaryResourceList.stream().map(diaryLike -> {

            DiaryResourceVo diaryResourceVo = new DiaryResourceVo();

            BeanUtils.copyProperties(diaryLike, diaryResourceVo);

            return diaryResourceVo;
        }).collect(Collectors.toList());

        return voList;
    }

}
