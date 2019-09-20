package com.dlion.life.base.service;

import com.dlion.life.base.dao.PunchCardDiaryMapper;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.bo.PunchCardDiarySearch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public void add(PunchCardDiary punchCardDiary) {

        punchCardDiaryMapper.insertSelective(punchCardDiary);
    }

    public List<PunchCardDiary> getByProjectId(Integer projectId) {

        return punchCardDiaryMapper.selectByProjectId(projectId);
    }

    public List<PunchCardDiary> listByUserId(Integer userId, Integer pageNo, Integer dataNum, Integer isDiaryCreator) {

        return punchCardDiaryMapper.listByUserId(userId, pageNo, dataNum, isDiaryCreator);
    }

    public List<PunchCardDiary> listByCno(Integer userId, Integer projectId, Integer pageNo, Integer dataNum) {

        return punchCardDiaryMapper.listByCno(userId, projectId, pageNo, dataNum);
    }
}
