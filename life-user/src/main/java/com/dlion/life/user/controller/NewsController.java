package com.dlion.life.user.controller;

import com.dlion.life.base.api.UnreadNewsCountApi;
import com.dlion.life.base.entity.UnreadNewsCount;
import com.dlion.life.common.constant.UnreadNewsType;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.user.model.UnreadNewsModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private UnreadNewsCountApi unreadNewsCountApi;

    /**
     * 获取用户未读消息数量
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/getUnreadNewsCount/{userId}")
    public Object getUnreadNews(@PathVariable Integer userId) {

        List<UnreadNewsCount> newsCountList = unreadNewsCountApi.getByUserId(userId);

        UnreadNewsModel model = new UnreadNewsModel();

        newsCountList.forEach(unreadNewsCount -> {

            if (Objects.equals(UnreadNewsType.NEWS_TYPE_LIKE, unreadNewsCount.getUnreadNewsType())) {
                model.setUnreadLikeNewsNum(unreadNewsCount.getUnreadNewsNum());
            }

            if (Objects.equals(UnreadNewsType.NEWS_TYPE_COMMENT, unreadNewsCount.getUnreadNewsType())) {
                model.setUnreadCommentNewsNum(unreadNewsCount.getUnreadNewsNum());
            }
        });

        return new ResponseModel(model);
    }

    @PutMapping("/setNewsReadStatus/{userId}")
    public Object setNewsReadStatus(@PathVariable Integer userId, @RequestParam String unreadNewsType) {

        List<UnreadNewsCount> newsCountList = unreadNewsCountApi.getByUserId(userId);

        unreadNewsCountApi.setNewsCount(userId, unreadNewsType, 0);

        return new ResponseModel();
    }


}
