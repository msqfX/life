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
    public User getUserById(Integer id) {
        return null;
    }

    @Override
    public User getByOpenId(String openId) {
        return null;
    }

    @Override
    public void addUser(User user) {

    }

}
