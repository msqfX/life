package com.dlion.life.punch.service;

import com.dlion.life.base.api.DiaryCommentApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.punch.model.AttendUserModel;
import com.dlion.life.punch.vo.CommentInfoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019-09-21
 */

@Service
public class CommentInfoService {

    @Autowired
    private DiaryCommentApi diaryCommentApi;

    @Autowired
    private UserApi userApi;

    public List<CommentInfoVo> listUserInfo(PunchCardDiary diary) {

        List<DiaryComment> commentList = diaryCommentApi.getByDiaryId(diary.getId());

        List<CommentInfoVo> list = commentList.stream().map(diaryComment -> {

            CommentInfoVo commentInfoVo = new CommentInfoVo();

            BeanUtils.copyProperties(diaryComment, commentInfoVo);
            commentInfoVo.setCreateTime(DateUtil.formatDate(diaryComment.getCreateTime(), DatePattern.YYYY_MM_DD_HH_mm_ss));

            User reviewerUser = userApi.getUserById(diaryComment.getReviewerId());
            User respondentUser = userApi.getUserById(diaryComment.getRespondentId());

            AttendUserModel reviewerUserInfo = new AttendUserModel();
            AttendUserModel respondentUserInfo = new AttendUserModel();

            BeanUtils.copyProperties(reviewerUser, reviewerUserInfo);
            BeanUtils.copyProperties(respondentUser, respondentUserInfo);

            commentInfoVo.setReviewer(reviewerUserInfo);
            commentInfoVo.setRespondent(respondentUserInfo);

            return commentInfoVo;
        }).collect(Collectors.toList());

        return list;
    }


}
