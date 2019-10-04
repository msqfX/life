package com.dlion.life.punch.service;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.base.entity.User;
import com.dlion.life.common.vo.Publisher;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李正元
 * @date 2019/10/3
 */
@Service
public class UserService {

    @Autowired
    private UserApi userApi;

    public Publisher getPublister(Integer userId) {

        Publisher publisher = new Publisher();

        User user = userApi.getUserById(userId);

        BeanUtils.copyProperties(user, publisher);

        return publisher;
    }

}
