package com.dlion.life.base.controller;

import com.dlion.life.base.api.SysRecommendProCoverImgApi;
import com.dlion.life.base.entity.SysRecommendProCoverImg;
import com.dlion.life.base.service.SysRecommendProCoverImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/10/3
 */
@RestController
public class SysRecommendProCoverImgApiController implements SysRecommendProCoverImgApi {

    @Autowired
    private SysRecommendProCoverImgService sysRecommendProCoverImgService;

    @Override
    public List<SysRecommendProCoverImg> list() {

        return sysRecommendProCoverImgService.list();
    }
}
