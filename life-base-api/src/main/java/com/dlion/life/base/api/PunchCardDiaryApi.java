package com.dlion.life.base.api;

import com.dlion.life.base.entity.PunchCardDiary;
import com.dlion.life.common.bo.DiarySearchPo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(value = "life-base")
public interface PunchCardDiaryApi {

    @PostMapping("/life-base-punchCardDiary/add")
    Integer add(@RequestBody PunchCardDiary punchCardDiary);

    @GetMapping("/life-base-punchCardDiary/listByProjectId")
    List<PunchCardDiary> getByProjectId(@RequestParam("projectId") Integer projectId);

    @GetMapping("/life-base-punchCardDiary/getByMoreLikeNum")
    List<PunchCardDiary> getByMoreLikeNum(@RequestParam("userId") Integer userId);

    @PostMapping("/life-base-punchCardDiary/listByUserId")
    List<PunchCardDiary> listByUserId(@RequestParam("userId") Integer userId,
                                      @RequestParam("pageNo") Integer pageNo,
                                      @RequestParam("dataNum") Integer dataNum,
                                      @RequestParam("isDiaryCreator") Integer isDiaryCreator);

    @GetMapping("/life-base-punchCardDiary/getByCno")
    List<PunchCardDiary> getByCno(@RequestParam("projectId") Integer projectId,
                                  @RequestParam("pageNo") Integer pageNo,
                                  @RequestParam("dataNum") Integer dataNum);

    @GetMapping("/life-base-punchCardDiary/getById")
    PunchCardDiary getById(@RequestParam("diaryId") Integer diaryId);

    @PutMapping("/life-base-punchCardDiary/update")
    void update(@RequestBody PunchCardDiary punchCardDiary);

    @DeleteMapping("/life-base-punchCardDiary/delete")
    void delete(@RequestParam("diaryId") Integer diaryId);

    /**
     * 日记列表，推荐列表
     *
     * @param diarySearchPo
     * @return
     */
    @GetMapping(value = "/life-base-diaryLike/list", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    List<PunchCardDiary> list(@RequestBody DiarySearchPo diarySearchPo);
}
