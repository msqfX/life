package com.dlion.life.punch.controller;

import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.model.PunchCardDiaryModel;
import com.dlion.life.common.model.ResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
