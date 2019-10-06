package com.dlion.life.base.service;

import com.dlion.life.base.dao.PunchCardDiaryMapper;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.bo.DiarySearchPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * 打卡日历
 *
 * @author 李正元
 * @date 2019/9/11
 */
@Service
public class PunchCardDiaryService {

    @Autowired
    private PunchCardDiaryMapper punchCardDiaryMapper;

    public Integer add(PunchCardDiary punchCardDiary) {

        punchCardDiaryMapper.insertSelective(punchCardDiary);
        return punchCardDiary.getId();
    }

    public List<PunchCardDiary> getByProjectId(Integer projectId) {

        return punchCardDiaryMapper.selectByProjectId(projectId);
    }

    public List<PunchCardDiary> listByUserId(Integer userId, Integer pageNo, Integer dataNum, Integer isDiaryCreator) {

        pageNo = (pageNo - 1) * dataNum;
        return punchCardDiaryMapper.listByUserId(userId, pageNo, dataNum, isDiaryCreator);
    }

    public List<PunchCardDiary> listByCno(Integer projectId, Integer pageNo, Integer dataNum) {

        pageNo = (pageNo - 1) * dataNum;
        return punchCardDiaryMapper.listByCno(projectId, pageNo, dataNum);
    }

    public PunchCardDiary getById(Integer diaryId) {

        return punchCardDiaryMapper.selectByPrimaryKey(diaryId);
    }

    public void update(PunchCardDiary punchCardDiary) {

        punchCardDiaryMapper.updateByPrimaryKeySelective(punchCardDiary);
    }

    public void delete(Integer diaryId) {

        punchCardDiaryMapper.deleteByPrimaryKey(diaryId);
    }

    public List<PunchCardDiary> list(DiarySearchPo diarySearchPo) {

        if (Objects.nonNull(diarySearchPo.getPageNo())) {
            diarySearchPo.setPageNo((diarySearchPo.getPageNo() - 1) * diarySearchPo.getPageNo());
        }
        return punchCardDiaryMapper.list(diarySearchPo);
    }

    public List<PunchCardDiary> listByTime(Integer userId, Integer projectId, Timestamp startTime, Timestamp endTime) {

        return punchCardDiaryMapper.listByTime(userId, projectId, startTime, endTime);
    }
}
