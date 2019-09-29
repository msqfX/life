package com.dlion.life.base.api;

import com.dlion.life.base.entity.UnreadNewsCount;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@FeignClient(value = "life-base")
public interface UnreadNewsCountApi {

    /**
     * 根据用户id查询
     *
     * @param userId 用户id
     * @return
     */
    @GetMapping("/life-base-unreadNewsCount/getByUserId")
    List<UnreadNewsCount> getByUserId(@RequestParam("userId") Integer userId);

    /**
     * 设置消息数量
     *
     * @param userId
     * @param unreadNewsType
     * @param num
     */
    @GetMapping("/life-base-unreadNewsCount/setNewsCount")
    void setNewsCount(@RequestParam("userId") Integer userId, @RequestParam("unreadNewsType") String unreadNewsType,
                      @RequestParam("num") int num);

    /**
     * 设置消息数量
     *
     * @param userId
     * @param unreadNewsType
     */
    @GetMapping("/life-base-unreadNewsCount/addNewsCount")
    void addNewsCount(@RequestParam("userId") Integer userId,@RequestParam("unreadNewsType") String unreadNewsType);
}
