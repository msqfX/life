package com.dlion.life.mbook.controller;

import com.dlion.life.base.api.AccountApi;
import com.dlion.life.base.entity.Account;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.enums.AccountTypeEnum;
import com.dlion.life.common.model.AccountModel;
import com.dlion.life.common.model.ResponseModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019-07-16
 */

@RestController("/mbook")
public class MBookController {

    @Autowired
    private AccountApi accountApi;

    @GetMapping("/listAccount")
    public Object listAccount(@RequestParam Integer userId) {

        if (Objects.isNull(userId)) {
            return new ResponseModel(ResultConstant.ERROR, "用户不存在");
        }

        List<Account> accountList = accountApi.listByUserId(userId);


        List<AccountModel> modelList = accountList.stream().map(e -> {

            AccountModel accountModel = new AccountModel();

            BeanUtils.copyProperties(e, accountModel);

            accountModel.setAbbreviation(e.getName().substring(0, 1));

            accountModel.setAccountTypeName(AccountTypeEnum.getName(e.getAccountType()));

            return accountModel;

        }).collect(Collectors.toList());

        return new ResponseModel(modelList);

    }

    @PostMapping("/addAccount")
    public Object addAccount(@RequestBody AccountModel accountModel) {

        Account account = new Account();

        BeanUtils.copyProperties(accountModel, account);

        accountApi.addAccount(account);

        return new ResponseModel();

    }

    @GetMapping("/{id}")
    public Object getById(@PathVariable Integer id, @RequestParam("userId") Integer userId) {

        Account account = accountApi.getById(id, userId);

        return new ResponseModel(account);

    }

    @PutMapping("/{id}")
    public Object update(@PathVariable Integer id, @RequestBody AccountModel accountModel) {

        Account temp = accountApi.getById(id, accountModel.getUserId());

        if (Objects.isNull(temp)) {
            return new ResponseModel(ResultConstant.ERROR, "记录不存在");
        }

        Account account = new Account();

        BeanUtils.copyProperties(accountModel, account);

        accountApi.update(account);

        return new ResponseModel();

    }

    @DeleteMapping("/{id}")
    public Object delete(@PathVariable Integer id) {

        accountApi.delete(id);

        return new ResponseModel();

    }


}
