package com.dlion.life.base.service;

import com.dlion.life.base.dao.UnreadNewsCountMapper;
import com.dlion.life.base.entity.UnreadNewsCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@Service
public class UnreadNewsCountService {

    @Autowired
    private UnreadNewsCountMapper unreadNewsCountMapper;

    public List<UnreadNewsCount> getByUserId(Integer userId) {

        return unreadNewsCountMapper.selectByUserId(userId);
    }

    public void setNewsCount(Integer userId, String unreadNewsType, int num) {

        unreadNewsCountMapper.setNewsCount(userId, unreadNewsType, num);
    }

    public void addNewsCount(Integer userId, String unreadNewsType) {

        unreadNewsCountMapper.addNewsCount(userId, unreadNewsType);

    }
}
