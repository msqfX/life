package com.dlion.life.punch.service;

import com.dlion.life.base.api.DiaryLikeApi;
import com.dlion.life.base.api.PunchCardDiaryApi;
import com.dlion.life.base.entity.DiaryLike;
import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.constant.RedisKey;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Service
public class PunchCardDiaryService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    public Boolean hasLike(Integer punchCardDiaryId, Integer userId) {

        DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(punchCardDiaryId, userId);

        return Objects.nonNull(diaryLike);
    }

    public void updateComment(PunchCardDiary punchCardDiary) {

        if (Objects.isNull(punchCardDiary)) {
            return;
        }

        punchCardDiaryApi.update(punchCardDiary);
    }

    public boolean todayPunchCard(Integer projectId, Integer userId) {

        LocalDate localDate = LocalDate.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // "1986-04-08"
        String todayStr = localDate.format(formatter);

        String hashKey = String.format(RedisKey.USER_PUNCH_CARD_DAY_KEY, userId);

        Object obj = hashOperations.get(hashKey, projectId);
        hashOperations.put(hashKey, projectId.toString(), todayStr);
        redisTemplate.expire(hashKey, 24, TimeUnit.HOURS);

        if (Objects.nonNull(obj)) {
            if (StringUtils.equals(todayStr, obj.toString())) {
                return true;
            }
        }

        return false;
    }

}
