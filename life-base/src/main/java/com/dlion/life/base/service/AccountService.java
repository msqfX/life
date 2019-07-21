package com.dlion.life.base.service;

import com.dlion.life.base.dao.AccountMapper;
import com.dlion.life.base.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-07-21
 */
@Service
public class AccountService {

    @Autowired
    private AccountMapper accountMapper;

    public void addAccount(Account account){

        accountMapper.insert(account);

    }

    public Account getById(Integer id, Integer userId){

        return accountMapper.selectById(id,userId);

    }

    public List<Account> listByUserId(Integer userId){

        return accountMapper.selectByUserId(userId);

    }

    public void update(Account account){

        accountMapper.update(account);

    }

    public void delete(Integer id){

        accountMapper.delete(id);

    }

}
