package com.dlion.life.punch.controller;

import com.dlion.life.base.api.ProIntrDetailInfoApi;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.punch.model.ProIntrDetailInfoModel;
import com.dlion.life.punch.service.FileService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * 圈子描述Controller
 *
 * @author 李正元
 * @date 2019/9/15
 */
@RestController
@RequestMapping("/api/projectIntr")
public class ProIntrDetailInfoController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProIntrDetailInfoApi proIntrDetailInfoApi;

    @Autowired
    private FileService fileService;

    /**
     * 添加圈子描述信息，文本
     *
     * @param proIntrDetailInfoModel 圈子描述信息
     * @return
     */
    @PostMapping
    public Object addProjectIntr(@RequestBody ProIntrDetailInfoModel proIntrDetailInfoModel) {

        ProIntrDetailInfo proIntrDetailInfo = new ProIntrDetailInfo();

        BeanUtils.copyProperties(proIntrDetailInfoModel, proIntrDetailInfo);

        proIntrDetailInfoApi.add(proIntrDetailInfo);

        return new ResponseModel();
    }

    /**
     * 圈子介绍文件上传
     *
     * @param request       请求体
     * @param multipartFile 文件对象
     * @return
     */
    @PostMapping(value = "/file/upload", consumes = "multipart/form-data")
    public Object upload(HttpServletRequest request, @RequestParam("file") MultipartFile multipartFile) {

        String projectId = request.getParameter("projectId");
        String sort = request.getParameter("sort");
        String type = request.getParameter("type");

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(sort) || StringUtils.isEmpty(type)) {
            return new ResponseModel(ResultConstant.ERROR, "参数异常");
        }

        if (!multipartFile.isEmpty()) {
            try {

                InputStream inputStream = multipartFile.getInputStream();
                String path = fileService.uploadFile(inputStream, CommonUtil.getFileName());

                ProIntrDetailInfo proIntrDetailInfo = new ProIntrDetailInfo();
                proIntrDetailInfo.setProjectId(Integer.valueOf(projectId));
                proIntrDetailInfo.setSort(Integer.valueOf(sort));
                proIntrDetailInfo.setType(Integer.valueOf(type));
                proIntrDetailInfo.setContent(path);

                proIntrDetailInfoApi.add(proIntrDetailInfo);

                Map<String, Object> result = new HashMap<>();
                result.put("fileUrl", path);

                logger.info("七牛云返回的图片链接:{}", path);

                return new ResponseModel(result);
            } catch (IOException e) {
                logger.error("上传图片错误,projectId:{}", projectId, e);
            }
        }
        return new ResponseModel(ResultConstant.ERROR, "上传失败");
    }


}
