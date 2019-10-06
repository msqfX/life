package com.dlion.life.punch.controller;

import com.dlion.life.base.api.DiaryCommentApi;
import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryComment;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.DiaryCommentModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.punch.model.DiaryCommentResultModel;
import com.dlion.life.punch.vo.ReviewerVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 打卡日历评论
 *
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/diaryComment")
public class DiaryCommentController {

    @Autowired
    private DiaryCommentApi diaryCommentApi;

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    @Autowired
    private UserApi userApi;

    /**
     * 日记评论
     *
     * @param diaryCommentModel
     * @return
     */
    @PostMapping
    public Object add(@RequestBody @Valid DiaryCommentModel diaryCommentModel) {

        PunchCardDiary punchCardDiary = punchCardDiaryApi.getById(diaryCommentModel.getDiaryId());
        if (Objects.isNull(punchCardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        DiaryComment diaryComment = new DiaryComment();

        BeanUtils.copyProperties(diaryCommentModel, diaryComment);

        Integer id = diaryCommentApi.add(diaryComment);

        // update comment_num
        PunchCardDiary newPunchCardDiary = new PunchCardDiary();
        newPunchCardDiary.setId(diaryCommentModel.getDiaryId());
        newPunchCardDiary.setCommentNum(punchCardDiary.getCommentNum() + 1);
        punchCardDiaryApi.update(newPunchCardDiary);

        DiaryCommentResultModel diaryCommentResultModel = new DiaryCommentResultModel();
        BeanUtils.copyProperties(diaryCommentModel, diaryCommentResultModel);
        diaryCommentResultModel.setCreateTime(DateUtil.formatDate(new Date(), DatePattern.YYYY_MM_DD_HH_mm_ss));
        diaryCommentResultModel.setId(id);

        User reviewerUser = userApi.getUserById(diaryCommentModel.getReviewerId());
        ReviewerVo reviewer = new ReviewerVo();
        BeanUtils.copyProperties(reviewerUser, reviewer);
        diaryCommentResultModel.setReviewer(reviewer);

        User respondentUser = userApi.getUserById(diaryCommentModel.getRespondentId());
        ReviewerVo respondent = new ReviewerVo();
        BeanUtils.copyProperties(respondentUser, respondent);
        diaryCommentResultModel.setRespondent(respondent);

        return new ResponseModel(diaryCommentResultModel);
    }

    @GetMapping("/getByDiaryId/{diaryId}")
    public Object getByDiaryId(@PathVariable Integer diaryId) {

        List<DiaryComment> commentList = diaryCommentApi.getByDiaryId(diaryId);

        List<DiaryCommentModel> modelList = commentList.stream().map(comment -> {

            DiaryCommentModel diaryCommentModel = new DiaryCommentModel();

            BeanUtils.copyProperties(comment, diaryCommentModel);

            return diaryCommentModel;
        }).collect(Collectors.toList());

        return new ResponseModel();
    }

    /**
     * 删除日记评论
     *
     * @param id
     * @param diaryCommentModel
     * @param request
     * @return
     */
    @DeleteMapping("{id}")
    @Transactional(rollbackFor = Exception.class)
    public Object delete(@PathVariable Integer id, @RequestBody DiaryCommentModel diaryCommentModel, HttpServletRequest request) {

        PunchCardDiary punchCardDiary = punchCardDiaryApi.getById(diaryCommentModel.getDiaryId());
        if (Objects.isNull(punchCardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        } else {
            String token = request.getHeader("token");
            User user = userApi.getUserByToken(token);

            DiaryComment diaryComment = diaryCommentApi.getById(id);
            if (Objects.nonNull(diaryComment) && Objects.nonNull(user)) {
                if (Objects.equals(diaryComment.getRespondentId(), user.getId())) {
                    diaryCommentApi.delete(id);
                }
            }
            List<DiaryComment> diaryCommentList = new ArrayList<>();
            if (Objects.equals(diaryComment.getPid(), 0)) {
                diaryCommentList = diaryCommentApi.listByPid(diaryCommentModel.getDiaryId(), id);
                diaryCommentList.stream().forEach(e -> {
                    diaryCommentApi.delete(e.getId());
                });
            }

            // 日记更新评论数量
            PunchCardDiary newPunchCardDiary = new PunchCardDiary();
            newPunchCardDiary.setId(punchCardDiary.getId());
            newPunchCardDiary.setCommentNum(punchCardDiary.getCommentNum() - 1 - diaryCommentList.size());
            punchCardDiaryApi.update(newPunchCardDiary);

            return new ResponseModel();
        }

    }

}
