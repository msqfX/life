package com.dlion.life.punch.controller;

import com.dlion.life.base.api.ProIntrDetailInfoApi;
import com.dlion.life.base.api.PunchCardProjectApi;
import com.dlion.life.base.entity.ProIntrDetailInfo;
import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.common.constant.CharacterConstant;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.punch.model.ProIntrDetailInfoModel;
import com.dlion.life.punch.service.FileService;
import com.dlion.life.punch.vo.DeleteProjectIntrVo;
import com.dlion.life.punch.vo.ProIntrBarchUpdateModel;
import com.qiniu.common.QiniuException;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

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

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    /**
     * 获取圈子介绍
     *
     * @param projectId 圈子ID
     * @return
     */
    @GetMapping
    public Object getProjectIntr(@RequestParam Integer projectId) {

        List<ProIntrDetailInfo> proIntrDetailInfoList = proIntrDetailInfoApi.getByProjectId(projectId);

        List<ProIntrDetailInfoModel> modelList = proIntrDetailInfoList.stream().map(proIntrDetailInfo -> {
            ProIntrDetailInfoModel proIntrDetailInfoModel = new ProIntrDetailInfoModel();
            BeanUtils.copyProperties(proIntrDetailInfo, proIntrDetailInfoModel);

            return proIntrDetailInfoModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

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

        Integer id = proIntrDetailInfoApi.add(proIntrDetailInfo);

        Map<String, Object> result = new HashMap<>(1);
        result.put("intrId", id);
        return new ResponseModel(result);
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

                Integer id = proIntrDetailInfoApi.add(proIntrDetailInfo);

                Map<String, Object> result = new HashMap<>();
                result.put("fileUrl", path);
                result.put("intrId", id);

                logger.info("七牛云返回的图片链接:{}", path);

                return new ResponseModel(result);
            } catch (IOException e) {
                logger.error("上传图片错误,projectId:{}", projectId, e);
            }
        }
        return new ResponseModel(ResultConstant.ERROR, "上传失败");
    }

    /**
     * 删除圈子介绍信息
     *
     * @return
     */
    @Transactional
    @DeleteMapping("/deleteProjectIntr")
    public Object deleteProjectIntr(@RequestBody @Valid DeleteProjectIntrVo deleteProjectIntrVo) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(deleteProjectIntrVo.getProjectId());
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        if (ArrayUtils.isNotEmpty(deleteProjectIntrVo.getDeleteIdList())) {
            Arrays.asList(deleteProjectIntrVo.getDeleteIdList()).stream().forEach(e -> {
                ProIntrDetailInfo proIntrDetailInfo = proIntrDetailInfoApi.getById(e);
                if (Objects.nonNull(proIntrDetailInfo)) {
                    if (Objects.equals(proIntrDetailInfo.getType(), 2)) {
                        //删除图片文件
                        String fileName = StringUtils.substringAfterLast(proIntrDetailInfo.getContent(), CharacterConstant.URL_SPLIT_STR);
                        try {
                            fileService.delete(fileName);
                        } catch (QiniuException ex) {
                            logger.error("文件删除失，detail:{}", proIntrDetailInfo, e);
                        }
                    }
                    proIntrDetailInfoApi.delete(e);
                }
            });
        }

        return new ResponseModel();
    }

    /**
     * 批量更新圈子介绍详情
     *
     * @param proIntrBarchUpdateModel
     * @return
     */
    @PutMapping("/batchUpdate")
    @Transactional(rollbackFor = Exception.class)
    public Object batchUpdate(@RequestBody ProIntrBarchUpdateModel proIntrBarchUpdateModel) {

        if (CollectionUtils.isEmpty(proIntrBarchUpdateModel.getProjectIntrInfo())) {
            return new ResponseModel();
        }

        proIntrBarchUpdateModel.getProjectIntrInfo().stream().forEach(proIntrDetailInfoModel -> {
            ProIntrDetailInfo proIntrDetailInfo = new ProIntrDetailInfo();
            BeanUtils.copyProperties(proIntrDetailInfoModel, proIntrDetailInfo);

            proIntrDetailInfoApi.update(proIntrDetailInfo);
        });

        return new ResponseModel();
    }

}
