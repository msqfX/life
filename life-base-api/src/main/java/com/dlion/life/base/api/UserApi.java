package com.dlion.life.base.api;

import com.dlion.life.base.fallback.UserApiFallback;
import com.dlion.life.base.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@FeignClient(value = "life-base",fallback = UserApiFallback.class)
public interface UserApi {

    /**
     * 熔断
     *
     * @param name
     * @return
     */
    @GetMapping("/hellpo")
    String hello(@RequestParam String name);

    @GetMapping("/getUserById")
    User getUserById(@RequestParam("id") Integer id);

}
