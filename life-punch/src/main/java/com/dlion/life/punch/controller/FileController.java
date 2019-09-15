package com.dlion.life.punch.controller;

import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.punch.service.FileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 文件服务
 *
 * @author 李正元
 * @date 2019/9/12
 */
@RestController
@RequestMapping("/api/file")
public class FileController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private FileService fileService;

    @RequestMapping("/upload")
    public Object upload(@RequestParam("file") MultipartFile multipartFile) {

        if (!multipartFile.isEmpty()) {
            try {
                InputStream inputStream = multipartFile.getInputStream();
                String path = fileService.uploadFile(inputStream, CommonUtil.getFileName());

                Map<String, Object> result = new HashMap<>();
                result.put("fileUrl", path);
                logger.info("七牛云返回的图片链接:{}", path);
                return new ResponseModel(result);
            } catch (IOException e) {
                logger.error("上传图片错误", e);
            }
        }
        return new ResponseModel(ResultConstant.ERROR, "上传失败");
    }

}
