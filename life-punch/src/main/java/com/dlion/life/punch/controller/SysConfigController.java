package com.dlion.life.punch.controller;

import com.dlion.life.base.api.SysRecommendProCoverImgApi;
import com.dlion.life.base.entity.SysRecommendProCoverImg;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.punch.model.SysRecommendProCoverImgModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 配置相关接口
 *
 * @author 李正元
 * @date 2019/10/3
 */
@RestController
@RequestMapping("/api/sysConfig")
public class SysConfigController {

    @Autowired
    private SysRecommendProCoverImgApi sysRecommendProCoverImgApi;

    /**
     * 获取系统推荐的圈子背景图片
     *
     * @return
     */
    @GetMapping("/getSysRecommendCoverImg")
    public Object getSysRecommendCoverImg() {

        List<SysRecommendProCoverImg> proCoverImgList = sysRecommendProCoverImgApi.list();

        List<SysRecommendProCoverImgModel> list = proCoverImgList.stream().map(sysRecommendProCoverImg -> {

            SysRecommendProCoverImgModel model = new SysRecommendProCoverImgModel();
            BeanUtils.copyProperties(sysRecommendProCoverImg, model);

            return model;
        }).collect(Collectors.toList());

        return new ResponseModel(list);
    }

}
