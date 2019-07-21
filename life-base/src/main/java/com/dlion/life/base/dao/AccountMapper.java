package com.dlion.life.base.dao;

import com.dlion.life.base.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019-07-21
 */
@Mapper
public interface AccountMapper {

    void insert(@Param("account") Account account);

    void update(@Param("account") Account account);

    List<Account> selectByUserId(@Param("userId") Integer userId);

    Account selectById(@Param("id") Integer id, @Param("userId") Integer userId);

    void delete(@Param("id") Integer id);

}
