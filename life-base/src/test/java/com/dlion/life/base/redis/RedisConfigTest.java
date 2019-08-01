package com.dlion.life.base.redis;

import com.dlion.life.base.compent.RedisUtils;
import com.dlion.life.common.model.UserModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis测试
 *
 * @author 李正元
 * @date 2019-08-02
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisConfigTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Resource
    private ValueOperations<String, Object> valueOperations;

    @Autowired
    private HashOperations<String, String, Object> hashOperations;

    @Autowired
    private ListOperations<String, Object> listOperations;

    @Autowired
    private SetOperations<String, Object> setOperations;

    @Autowired
    private ZSetOperations<String, Object> zSetOperations;

    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void testObj() throws Exception {

        UserModel userModel = new UserModel();

        userModel.setOpenId("sfesf");
        userModel.setAvatarUrl("sfesfesf");

        ValueOperations<String, Object> operations = redisTemplate.opsForValue();

       /* operations.get("");  //里面均提供了
        operations.set("");  //里面提供了过个重载的方法.例如: set(K key, V value, long timeout, TimeUnit unit);等等*/

        redisUtils.expireKey("name", 20, TimeUnit.SECONDS);

        String key = "user_info_" + userModel.getOpenId();

        operations.set(key, userModel);

        UserModel vo = (UserModel) operations.get(key);

        System.out.println(vo);
    }

    @Test
    public void testSetOperation() throws Exception {

        UserModel userModel = new UserModel();
        userModel.setProvince("陕西");
        userModel.setId(1);

        UserModel userModel2 = new UserModel();
        userModel2.setProvince("陕西");
        userModel2.setId(2);

        setOperations.add("user:test", userModel, userModel2);

        Set<Object> result = setOperations.members("user:test");

        System.out.println(result);
    }

    @Test
    public void HashOperations() throws Exception {

        UserModel userModel = new UserModel();
        userModel.setProvince("陕西");
        userModel.setId(1);

        hashOperations.put("hash:user", userModel.getOpenId(), userModel);

        System.out.println(hashOperations.get("hash:user", userModel.getOpenId()));
    }

    @Test
    public void ListOperations() throws Exception {

        UserModel userModel = new UserModel();
        userModel.setProvince("陕西");
        userModel.setId(1);

//        listOperations.leftPush("list:user",userVo);
//        System.out.println(listOperations.leftPop("list:user"));
        // pop之后 值会消失
        System.out.println(listOperations.leftPop("list:user"));
    }


}
