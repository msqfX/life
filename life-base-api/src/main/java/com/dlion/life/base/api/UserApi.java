package com.dlion.life.base.api;

import com.dlion.life.base.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@FeignClient(name = "life-base-user", url = "http://localhost:8081")
public interface UserApi {

    @GetMapping("/life-base-user/getUserById")
    User getUserById(@RequestParam("id") Integer id);

    @GetMapping("/life-base-user/getByOpenId")
    User getByOpenId(@RequestParam("openId") String openId);

    @PostMapping("/life-base-user/addUser")
    Integer addUser(@RequestBody User user);

    @PostMapping("/life-base-user/updateUser")
    void updateUser(@RequestBody User user);

}
