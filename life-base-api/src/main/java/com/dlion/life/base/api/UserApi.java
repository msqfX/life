package com.dlion.life.base.api;

import com.dlion.life.base.fallback.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@FeignClient(name = "life-base", fallback = UserApiFallback.class)
public interface UserApi {

    /**
     * 熔断
     *
     * @param name
     * @return
     */
    @GetMapping("/hello")
    String hello(@RequestParam String name);

}
