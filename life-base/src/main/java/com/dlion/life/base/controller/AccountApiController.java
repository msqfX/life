package com.dlion.life.base.controller;

import com.dlion.life.base.api.AccountApi;
import com.dlion.life.base.entity.Account;
import com.dlion.life.base.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-07-21
 */

@RestController
public class AccountApiController implements AccountApi {

    @Autowired
    private AccountService accountService;

    @Override
    public void addAccount(@RequestBody Account account) {

        accountService.addAccount(account);

    }

    @Override
    public Account getById(Integer id, Integer userId) {

        return accountService.getById(id,userId);

    }

    @Override
    public List<Account> listByUserId(Integer userId) {

        return accountService.listByUserId(userId);

    }

    @Override
    public void update(@RequestBody Account account) {

        accountService.update(account);

    }

    @Override
    public void delete(Integer id) {

        accountService.delete(id);

    }
}
