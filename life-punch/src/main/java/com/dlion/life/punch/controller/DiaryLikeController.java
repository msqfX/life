package com.dlion.life.punch.controller;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.punch.model.CancelLikeModel;
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

    /**
     * 喜欢
     *
     * @param diaryLike
     * @return
     */
    @PostMapping("/like")
    public Object like(@RequestBody DiaryLike diaryLike) {

        DiaryLike like = diaryLikeApi.getByDiaryIdAndUserId(diaryLike.getDiaryId(), diaryLike.getLikedUserId());
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

        diaryLikeApi.delete(cancelLikeModel.getLikeRecordId());

        return new ResponseModel();
    }

}
