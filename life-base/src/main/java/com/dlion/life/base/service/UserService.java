package com.dlion.life.base.service;

import com.dlion.life.base.dao.UserMapper;
import com.dlion.life.base.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void addUser(User user){
        userMapper.inserUser(user);
    }

    public User getUser(Integer id){
        return userMapper.selectUserById(id);
    }

    public void updateUser(User user){
        userMapper.updateUser(user);
    }

}
