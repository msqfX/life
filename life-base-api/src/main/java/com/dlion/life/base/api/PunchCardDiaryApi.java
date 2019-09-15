package com.dlion.life.base.api;

import com.dlion.life.base.entity.PunchCardDiary;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(name = "life-base-punchCardDiary", url = "localhost:8081")
public interface PunchCardDiaryApi {

    @PostMapping("/life-base-punchCardDiary/add")
    void add(@RequestBody PunchCardDiary punchCardDiary);

    @GetMapping("/life-base-punchCardDiary/listByProjectId")
    List<PunchCardDiary> getByProjectId(@RequestParam("projectId") Integer projectId);

    @GetMapping("/life-base-punchCardDiary/getByMoreLikeNum")
    List<PunchCardDiary> getByMoreLikeNum(@RequestParam("userId") Integer userId);

    @PostMapping("/life-base-punchCardDiary/listByUserId")
    List<PunchCardDiary> listByUserId(@RequestParam("userId") Integer userId, @RequestParam("pageNo") Integer pageNo,
                                      @RequestParam("dataNum") Integer dataNum, @RequestParam("isDiaryCreator") Integer isDiaryCreator);
}
