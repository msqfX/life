package com.dlion.life.punch.controller;

import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.bo.PunchCardDiarySearch;
import com.dlion.life.common.model.PunchCardDiaryModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.punch.model.MyPunchCardDiaryModel;
import com.dlion.life.punch.service.DiaryResourceService;
import com.dlion.life.punch.service.PunchCardDiaryService;
import com.dlion.life.punch.service.PunchCardProjectService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/punchCardDiary")
public class PunchCardDiaryController {

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    @Autowired
    private PunchCardDiaryService punchCardDiaryService;

    @Autowired
    private PunchCardProjectService punchCardProjectService;

    @Autowired
    private DiaryResourceService diaryResourceService;

    @PostMapping
    public Object add(@RequestBody PunchCardDiaryModel punchCardDiaryModel) {

        PunchCardDiary punchCardDiary = new PunchCardDiary();

        BeanUtils.copyProperties(punchCardDiaryModel, punchCardDiary);

        punchCardDiaryApi.add(punchCardDiary);

        return new ResponseModel();
    }

    @GetMapping("/getByProjectId/{projectId}")
    public Object getByProjectId(@PathVariable Integer projectId) {

        List<PunchCardDiary> cardDiaryList = punchCardDiaryApi.getByProjectId(projectId);

        List<PunchCardDiaryModel> modelList = cardDiaryList.stream().map(punchCardDiary -> {

            PunchCardDiaryModel punchCardDiaryModel = new PunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, punchCardDiaryModel);

            return punchCardDiaryModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    @GetMapping("/listUserPunchCardDiary")
    public Object listUserPunchCardDiary(PunchCardDiarySearch punchCardDiarySearch) {

        List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.listByUserId(punchCardDiarySearch.getUserId(),
                punchCardDiarySearch.getPageNo(), punchCardDiarySearch.getDataNum(), punchCardDiarySearch.getIsDiaryCreator());

        List<MyPunchCardDiaryModel> modelList = punchCardDiaryList.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);

            myPunchCardDiaryModel.setHaveLike(punchCardDiaryService.hasLike(punchCardDiary.getId(), punchCardDiarySearch.getUserId()));

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(punchCardProjectService.getPublister(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setTenLikeInfo(new ArrayList());

            myPunchCardDiaryModel.setAllCommentInfo(new ArrayList());

            return myPunchCardDiaryModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }


}
