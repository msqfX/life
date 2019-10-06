package com.dlion.life.base.controller;

import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.service.DiaryLikeService;
import com.dlion.life.base.service.DiaryResourceService;
import com.dlion.life.base.service.PunchCardDiaryService;
import com.dlion.life.common.bo.DiarySearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
public class PunchCardDiaryApiController implements PunchCardDiaryApi {

    @Autowired
    private PunchCardDiaryService punchCardDiaryService;

    @Autowired
    private DiaryResourceService diaryResourceService;

    @Autowired
    private DiaryLikeService diaryLikeService;

    @Override
    public Integer add(@RequestBody PunchCardDiary punchCardDiary) {

        return punchCardDiaryService.add(punchCardDiary);
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

    @Override
    public List<PunchCardDiary> getByCno(Integer projectId, Integer pageNo, Integer dataNum) {

        return punchCardDiaryService.listByCno(projectId, pageNo, dataNum);
    }

    @Override
    public PunchCardDiary getById(Integer diaryId) {

        return punchCardDiaryService.getById(diaryId);
    }

    @Override
    public void update(@RequestBody PunchCardDiary punchCardDiary) {

        punchCardDiaryService.update(punchCardDiary);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Integer diaryId) {

        punchCardDiaryService.delete(diaryId);

        //删除日记的资源记录
        diaryResourceService.deleteByDiaryId(diaryId);

        //删除日记的点赞记录
        diaryLikeService.deleteByDiaryId(diaryId);

    }

    @Override
    public List<PunchCardDiary> list(@RequestBody DiarySearchPo diarySearchPo) {

        return punchCardDiaryService.list(diarySearchPo);
    }

    @Override
    public List<PunchCardDiary> listByTime(Integer userId, Integer projectId, Timestamp startTime, Timestamp endTime) {

        return punchCardDiaryService.listByTime(userId, projectId, startTime, endTime);
    }
}
