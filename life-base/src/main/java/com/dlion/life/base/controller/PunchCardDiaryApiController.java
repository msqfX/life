package com.dlion.life.base.controller;

import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.service.PunchCardDiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
public class PunchCardDiaryApiController implements PunchCardDiaryApi {

    @Autowired
    private PunchCardDiaryService punchCardDiaryService;

    @Override
    public void add(@RequestBody PunchCardDiary punchCardDiary) {

        punchCardDiaryService.add(punchCardDiary);
    }

    @Override
    public List<PunchCardDiary> getByProjectId(Integer projectId) {

        return punchCardDiaryService.getByProjectId(projectId);
    }

    @Override
    public List<PunchCardDiary> getByMoreLikeNum(Integer userId) {
        return null;
    }

    @Override
    public List<PunchCardDiary> listByUserId(Integer userId, Integer pageNo, Integer dataNum, Integer isDiaryCreator) {

        return punchCardDiaryService.listByUserId(userId, pageNo, dataNum, isDiaryCreator);
    }
}
