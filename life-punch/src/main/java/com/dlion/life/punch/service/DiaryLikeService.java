package com.dlion.life.punch.service;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.User;
import com.dlion.life.punch.vo.Admirer;
import com.dlion.life.punch.vo.DiaryLikeInfoVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019-09-21
 */
@Service
public class DiaryLikeService {

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private UserApi userApi;


    public List<DiaryLikeInfoVo> listLikeInfo(Integer diaryId) {

        List<DiaryLike> diaryLikeList = diaryLikeApi.listByDiaryId(diaryId);

        List<DiaryLikeInfoVo> diaryLikeInfoVos = diaryLikeList.stream().map(diaryLike -> {

            DiaryLikeInfoVo diaryLikeInfoVo = new DiaryLikeInfoVo();
            User user = userApi.getUserById(diaryLike.getAdmirerId());
            diaryLikeInfoVo.setId(diaryLike.getId());
            Admirer admirer = new Admirer();
            admirer.setId(user.getId());
            admirer.setNickName(user.getNickName());
            diaryLikeInfoVo.setAdmirer(admirer);

            return diaryLikeInfoVo;
        }).collect(Collectors.toList());

        return diaryLikeInfoVos;
    }


}
