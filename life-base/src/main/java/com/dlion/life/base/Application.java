package com.dlion.life.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author 李正元
 * @date 2019-06-08
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.dlion.life"})
//@EnableBinding({Source.class})
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
