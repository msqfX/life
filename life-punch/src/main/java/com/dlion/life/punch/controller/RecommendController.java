package com.dlion.life.punch.controller;

import com.dlion.life.common.model.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 系统推荐
 *
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/recommend")
public class RecommendController {

    /**
     * 打卡系统推荐列表
     *
     * @param dataNum  页数
     * @param nextPage 第几页
     * @return
     */
    @GetMapping("/listProject")
    public Object listProject(Integer dataNum, Integer nextPage) {

        return new ResponseModel();
    }

    @GetMapping("/listDiary")
    public Object listDiary(Integer dataNum, Integer nextPage) {

        return new ResponseModel();
    }

}
