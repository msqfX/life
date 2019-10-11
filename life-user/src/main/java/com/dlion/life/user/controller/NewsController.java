package com.dlion.life.user.controller;

import com.dlion.life.base.api.DiaryCommentApi;
import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.UnreadNewsCountApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.annotation.LoginUser;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.constant.UnreadNewsType;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.user.model.*;
import com.dlion.life.user.service.UserNewsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@RestController
@RequestMapping("/api/news")
public class NewsController {

    @Autowired
    private UnreadNewsCountApi unreadNewsCountApi;

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private DiaryCommentApi diaryCommentApi;
    ;

    @Autowired
    private UserApi userApi;

    @Autowired
    private UserNewsService userNewsService;

    /**
     * 获取用户未读消息数量
     *
     * @param loginUser 当前登录用户
     * @return
     */
    @GetMapping("/getUnreadNewsCount")
    public Object getUnreadNews(@LoginUser User loginUser) {

        if (Objects.isNull(loginUser)) {
            return new ResponseModel(new UnreadNewsModel());
        }

        UnreadNewsModel model = new UnreadNewsModel();
        model.setUnreadCommentNewsNum(userNewsService.getCommentCount(loginUser.getId()));
        model.setUnreadLikeNewsNum(userNewsService.getLikeCount(loginUser.getId()));

        return new ResponseModel(model);
    }

    @PutMapping("/setNewsReadStatus")
    public Object setNewsReadStatus(@LoginUser User loginUser, @RequestParam String unreadNewsType) {

        if (Objects.isNull(loginUser)) {
            return new ResponseModel();
        }

        if (Objects.equals(UnreadNewsType.NEWS_TYPE_LIKE, unreadNewsType)) {
            userNewsService.resetLikeCount(loginUser.getId());
        }

        if (Objects.equals(UnreadNewsType.NEWS_TYPE_COMMENT, unreadNewsType)) {
            userNewsService.resetCommentCount(loginUser.getId());
        }

        return new ResponseModel();
    }

    /**
     * 获取用户评论列表
     *
     * @param loginUser 当前登录用户
     * @return
     */
    @GetMapping("/getMyCommentedList")
    public Object getMyCommentedList(@LoginUser User loginUser) {

        if (Objects.isNull(loginUser)) {
            return new ResponseModel();
        }

        List<DiaryComment> diaryCommentList = diaryCommentApi.ListByRespondentId(loginUser.getId());

        List<DiaryCommentModel> diaryCommentModels = diaryCommentList.stream().map(diaryComment -> {
            DiaryCommentModel diaryCommentModel = new DiaryCommentModel();

            BeanUtils.copyProperties(diaryComment, diaryCommentModel);
            diaryCommentModel.setCreateTime(DateUtil.formatDate(diaryComment.getCreateTime(), DatePattern.YYYY_MM_DD_HH_mm_ss));

            User user = userApi.getUserById(diaryComment.getReviewerId());

            ReviewerModel reviewerModel = new ReviewerModel();
            BeanUtils.copyProperties(user, reviewerModel);
            diaryCommentModel.setReviewer(reviewerModel);

            return diaryCommentModel;
        }).collect(Collectors.toList());


        return new ResponseModel(diaryCommentModels);
    }

    /**
     * 获取用户点赞列表
     *
     * @param loginUser
     * @return
     */
    @GetMapping("/getMyLikedList")
    public Object getMyLikedList(@LoginUser User loginUser) {

        if (Objects.isNull(loginUser)) {
            return new ResponseModel();
        }

        List<DiaryLike> diaryLikes = diaryLikeApi.listByLikedUserId(loginUser.getId());

        List<DiaryLikeModel> diaryLikeModels = diaryLikes.stream().map(diaryLike -> {
            DiaryLikeModel diaryLikeModel = new DiaryLikeModel();

            BeanUtils.copyProperties(diaryLike, diaryLikeModel);

            User user = userApi.getUserById(diaryLike.getAdmirerId());
            AdmirerModel admirer = new AdmirerModel();
            BeanUtils.copyProperties(user, admirer);

            diaryLikeModel.setAdmirer(admirer);

            return diaryLikeModel;
        }).collect(Collectors.toList());

        return new ResponseModel(diaryLikeModels);
    }


}
