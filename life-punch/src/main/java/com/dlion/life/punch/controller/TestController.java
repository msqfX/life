package com.dlion.life.punch.controller;

import com.dlion.life.base.api.UserApi;
import com.dlion.life.common.model.ResponseModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * @author 李正元
 * @date 2019-06-08
 */
@RestController
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserApi userApi;

    @Autowired
    LoadBalancerClient loadBalancerClient;

    @GetMapping("/hello")
    public String hello(@RequestParam String name) {
        logger.info("invoked name = " + name);
        return "hello " + name+"from feign "+userApi.hello("fesfe");
    }

    @GetMapping("/test")
    public String test() {
        // 通过spring cloud common中的负载均衡接口选取服务提供节点实现接口调用
        ServiceInstance serviceInstance = loadBalancerClient.choose("life-base");
        String url = serviceInstance.getUri() + "/hello?name=" + "didi";
        RestTemplate restTemplate = new RestTemplate();
        String result = restTemplate.getForObject(url, String.class);
        return "Invoke : " + url + ", return : " + result;
    }


    @GetMapping("/getUser")
    public Object getUserById(){
        return new ResponseModel(userApi.getUserById(4));
    }

}
