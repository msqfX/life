package com.dlion.life.punch.config;

import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author 李正元
 * @date 2019/9/12
 */
@Configuration
public class QiniuUploadFileConfig {

    @Value("${qiniu.accessKey}")
    private String accessKey;

    @Value("${qiniu.secretKey}")
    private String secretKey;

    /**
     * 配置自己空间所在的区域
     */
    @Bean
    public com.qiniu.storage.Configuration qiniuConfig() {

        return new com.qiniu.storage.Configuration(Region.huabei());
    }

    /**
     * 构建一个七牛上传工具实例
     */
    @Bean
    public UploadManager uploadManager() {

        return new UploadManager(qiniuConfig());
    }

    /**
     * 认证信息实例
     *
     * @return
     */
    @Bean
    public Auth auth() {

        return Auth.create(accessKey, secretKey);
    }

    /**
     * 构建七牛空间管理实例
     */
    @Bean
    public BucketManager bucketManager() {

        return new BucketManager(auth(), qiniuConfig());
    }
}
