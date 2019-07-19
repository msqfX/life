package com.dlion.life.base.fallback;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import org.springframework.stereotype.Component;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@Component
public class UserApiFallback implements UserApi {

    @Override
    public String hello(String name) {
        return "请求失败，请检查网络";
    }

    @Override
    public User getUserById(Integer id) {
        return null;
    }

}
