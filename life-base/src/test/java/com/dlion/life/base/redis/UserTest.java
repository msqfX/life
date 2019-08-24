package com.dlion.life.base.redis;

import com.dlion.life.base.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李正元
 * @date 2019/8/23
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        String openId = "onCAd5Pn1Q55MiSDpAi_NwFIa7WE";
        System.out.println(userService.getByOpenId(openId));


    }

}
