package com.dlion.life.base.controller;

import com.dlion.life.base.api.UnreadNewsCountApi;
import com.dlion.life.base.entity.UnreadNewsCount;
import com.dlion.life.base.service.UnreadNewsCountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@RestController
public class UnreadNewsCountApiController implements UnreadNewsCountApi {

    @Autowired
    private UnreadNewsCountService unreadNewsCountService;

    @Override
    public List<UnreadNewsCount> getByUserId(Integer userId) {

        return unreadNewsCountService.getByUserId(userId);
    }

    @Override
    public void setNewsCount(Integer userId, String unreadNewsType, int num) {

        unreadNewsCountService.setNewsCount(userId, unreadNewsType, num);
    }

    @Override
    public void addNewsCount(Integer userId, String unreadNewsType) {

        unreadNewsCountService.addNewsCount(userId, unreadNewsType);
    }
}
