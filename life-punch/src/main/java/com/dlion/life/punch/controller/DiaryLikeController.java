package com.dlion.life.punch.controller;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.annotation.Login;
import com.dlion.life.common.annotation.LoginUser;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.punch.model.CancelLikeModel;
import com.dlion.life.punch.service.UserNewsService;
import com.dlion.life.punch.vo.Admirer;
import com.dlion.life.punch.vo.DiaryLikeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

/**
 * @author 李正元
 * @date 2019-09-22
 */

@RestController
@RequestMapping("/api/diaryLike")
public class DiaryLikeController {

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private UserApi userApi;

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    @Autowired
    private UserNewsService userNewsService;

    /**
     * 点赞
     *
     * @param diaryLike
     * @return
     */
    @PostMapping("/like")
    @Login
    public Object like(@LoginUser User loginUser, @RequestBody DiaryLike diaryLike) {

        PunchCardDiary punchCardDiary = punchCardDiaryApi.getById(diaryLike.getDiaryId());
        if (Objects.isNull(punchCardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        DiaryLike like = diaryLikeApi.getByDiaryIdAndUserId(diaryLike.getDiaryId(), loginUser.getId());
        if (Objects.nonNull(like)) {
            return new ResponseModel(ResultConstant.ERROR, "已点过赞");
        }

        Integer id = diaryLikeApi.like(diaryLike);

        DiaryLikeInfoVo diaryLikeInfoVo = new DiaryLikeInfoVo();
        diaryLikeInfoVo.setId(id);

        User user = userApi.getUserById(diaryLike.getAdmirerId());

        Admirer admirer = new Admirer();
        admirer.setId(user.getId());
        admirer.setNickName(user.getNickName());

        diaryLikeInfoVo.setAdmirer(admirer);

        //update like_num
        PunchCardDiary newPunchCardDiary = new PunchCardDiary();
        newPunchCardDiary.setId(diaryLike.getDiaryId());
        newPunchCardDiary.setLikeUserNum(punchCardDiary.getLikeUserNum() + 1);
        punchCardDiaryApi.update(newPunchCardDiary);

        userNewsService.setLikeCount(diaryLike.getLikedUserId());

        return new ResponseModel(diaryLikeInfoVo);
    }

    /**
     * 取消喜欢
     *
     * @param cancelLikeModel
     * @return
     */
    @DeleteMapping("/cancelLike")
    public Object cancelLike(@RequestBody CancelLikeModel cancelLikeModel) {

        PunchCardDiary punchCardDiary = punchCardDiaryApi.getById(cancelLikeModel.getDiaryId());
        if (Objects.isNull(punchCardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        diaryLikeApi.delete(cancelLikeModel.getLikeRecordId());

        //update like_num
        PunchCardDiary newPunchCardDiary = new PunchCardDiary();
        newPunchCardDiary.setId(cancelLikeModel.getDiaryId());
        newPunchCardDiary.setLikeUserNum(punchCardDiary.getLikeUserNum() - 1);
        punchCardDiaryApi.update(newPunchCardDiary);

        return new ResponseModel();
    }

}
