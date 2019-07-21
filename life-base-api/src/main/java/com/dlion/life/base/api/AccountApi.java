package com.dlion.life.base.api;

import com.dlion.life.base.entity.Account;
import com.dlion.life.base.fallback.UserApiFallback;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-07-21
 */
@FeignClient(name = "life-base-account",url = "http://localhost:8081")
public interface AccountApi {

    @PostMapping("/life-base-account/addAccount")
    void addAccount(@RequestBody Account account);

    @GetMapping("/life-base-account/getById")
    //@RequestLine("/GET /account/getById")
    Account getById(@RequestParam("id") Integer id, @RequestParam("userId") Integer userId);

    @GetMapping("/life-base-account/listByUserId")
    //@RequestLine("/GET /account/listByUserId")
    List<Account> listByUserId(@RequestParam("userId") Integer userId);

    @PutMapping("/life-base-account/update")
    void update(@RequestBody Account account);

    @DeleteMapping("/life-base-account/delete")
    void delete(@RequestParam("id") Integer id);

}
