package com.dlion.life.punch.controller;

import com.dlion.life.base.api.*;
import com.dlion.life.base.entity.*;
import com.dlion.life.common.bo.PunchCardDiarySearch;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.DiaryCommentModel;
import com.dlion.life.common.model.PunchCardDiaryModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.punch.model.DiaryDetailModel;
import com.dlion.life.punch.model.MyPunchCardDiaryModel;
import com.dlion.life.punch.service.*;
import com.dlion.life.punch.vo.DiaryProjectInfoVo;
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
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@RestController
@RequestMapping("/api/punchCardDiary")
public class PunchCardDiaryController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PunchCardDiaryApi punchCardDiaryApi;

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    @Autowired
    private DiaryLikeApi diaryLikeApi;

    @Autowired
    private UserProjectRecordApi userProjectRecordApi;

    @Autowired
    private DiaryResourceApi diaryResourceApi;

    @Autowired
    private DiaryCommentApi diaryCommentApi;

    @Autowired
    private PunchCardDiaryService punchCardDiaryService;

    @Autowired
    private PunchCardProjectService punchCardProjectService;

    @Autowired
    private DiaryResourceService diaryResourceService;

    @Autowired
    private DiaryLikeService diaryLikeService;

    @Autowired
    private CommentInfoService commentInfoService;

    @Autowired
    private FileService fileService;

    @PostMapping
    public Object add(@RequestBody PunchCardDiaryModel punchCardDiaryModel) {

        PunchCardDiary punchCardDiary = new PunchCardDiary();

        BeanUtils.copyProperties(punchCardDiaryModel, punchCardDiary);

        if (0 == punchCardDiaryModel.getIsRepairDiary()) {
            punchCardDiary.setPunchCardTime(new Date());
            punchCardDiary.setRepairPunchCardTime(new Date());
        } else {
            punchCardDiary.setRepairPunchCardTime(new Date());
        }

        int id = punchCardDiaryApi.add(punchCardDiary);

        Map<String, Object> result = new HashMap<>();
        result.put("id", id);

        return new ResponseModel(result);
    }

    @GetMapping("/getByProjectId/{projectId}")
    public Object getByProjectId(@PathVariable Integer projectId) {

        List<PunchCardDiary> cardDiaryList = punchCardDiaryApi.getByProjectId(projectId);

        List<PunchCardDiaryModel> modelList = cardDiaryList.stream().map(punchCardDiary -> {

            PunchCardDiaryModel punchCardDiaryModel = new PunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, punchCardDiaryModel);

            return punchCardDiaryModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    @GetMapping("/listUserPunchCardDiary")
    public Object listUserPunchCardDiary(PunchCardDiarySearch punchCardDiarySearch) {

        List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.listByUserId(punchCardDiarySearch.getUserId(),
                punchCardDiarySearch.getPageNo(), punchCardDiarySearch.getDataNum(), punchCardDiarySearch.getIsDiaryCreator());

        List<MyPunchCardDiaryModel> modelList = punchCardDiaryList.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);
            myPunchCardDiaryModel.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm));

            myPunchCardDiaryModel.setHaveLike(punchCardDiaryService.hasLike(punchCardDiary.getId(), punchCardDiarySearch.getUserId()));

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(punchCardProjectService.getPublister(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setTenLikeInfo(new ArrayList());

            myPunchCardDiaryModel.setAllCommentInfo(new ArrayList());

            return myPunchCardDiaryModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    /**
     * 圈子详情打卡日记列表ø
     *
     * @param userId
     * @param projectId
     * @param pageNo
     * @param dataNum
     * @return
     */
    @GetMapping("/getDiaryListByProjectId")
    public Object getDiaryListByProjectId(@RequestParam Integer userId, @RequestParam Integer projectId,
                                          @RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        List<PunchCardDiary> punchCardDiaries = punchCardDiaryApi.getByCno(projectId, pageNo, dataNum);

        List<MyPunchCardDiaryModel> modelList = punchCardDiaries.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);

            myPunchCardDiaryModel.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm));

            myPunchCardDiaryModel.setHaveLike(punchCardDiaryService.hasLike(punchCardDiary.getId(), userId));

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(punchCardProjectService.getPublister(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setTenLikeInfo(diaryLikeService.listLikeInfo(punchCardDiary.getId()));

            myPunchCardDiaryModel.setAllCommentInfo(commentInfoService.listUserInfo(punchCardDiary));

            return myPunchCardDiaryModel;

        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }


    private List<MyPunchCardDiaryModel> dataConvert(List<PunchCardDiary> punchCardDiaryList) {

        List<MyPunchCardDiaryModel> modelList = punchCardDiaryList.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);

            myPunchCardDiaryModel.setHaveLike(punchCardDiaryService.hasLike(punchCardDiary.getId(), punchCardDiary.getUserId()));

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(punchCardProjectService.getPublister(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setTenLikeInfo(new ArrayList());

            myPunchCardDiaryModel.setAllCommentInfo(new ArrayList());

            return myPunchCardDiaryModel;
        }).collect(Collectors.toList());

        return modelList;
    }

    /**
     * 获取日记详情
     *
     * @param diaryId
     * @param visitorId
     * @return
     */
    @GetMapping("/getDiaryDetailInfoById")
    public Object getDiaryDetailInfoById(@RequestParam Integer diaryId, @RequestParam Integer visitorId) {

        PunchCardDiary punchCardDiary = punchCardDiaryApi.getById(diaryId);
        if (Objects.isNull(punchCardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        DiaryDetailModel diaryDetailModel = new DiaryDetailModel();

        BeanUtils.copyProperties(punchCardDiary, diaryDetailModel);

        diaryDetailModel.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm_ss));

        DiaryProjectInfoVo projectInfoVo = new DiaryProjectInfoVo();
        PunchCardProject punchCardProject = punchCardProjectApi.getById(punchCardDiary.getProjectId());
        BeanUtils.copyProperties(punchCardProject, projectInfoVo);
        diaryDetailModel.setProjectInfo(projectInfoVo);

        DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(diaryId, visitorId);
        if (Objects.nonNull(diaryLike)) {
            diaryDetailModel.setLikeRecordId(diaryLike.getId());
            diaryDetailModel.setHaveLike(true);
        } else {
            diaryDetailModel.setHaveLike(false);
        }

        UserProjectRecord projectRecord = userProjectRecordApi.getByUserId(visitorId, punchCardProject.getId());
        if (Objects.nonNull(projectRecord)) {
            diaryDetailModel.setExistAttendProject(true);
        } else {
            diaryDetailModel.setExistAttendProject(false);
        }

        diaryDetailModel.setDiaryResource(diaryResourceService.listByDiaryId(diaryId));

        diaryDetailModel.setPublisher(punchCardProjectService.getPublister(punchCardProject.getId()));

        diaryDetailModel.setAllLikeInfo(diaryLikeService.listLikeInfo(diaryId));

        diaryDetailModel.setAllCommentInfo(commentInfoService.listUserInfo(punchCardDiary));

        return new ResponseModel(diaryDetailModel);
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
        String diaryId = request.getParameter("diaryId");
        String resourceType = request.getParameter("resourceType");

        if (StringUtils.isEmpty(projectId) || StringUtils.isEmpty(diaryId) || StringUtils.isEmpty(resourceType)) {
            return new ResponseModel(ResultConstant.ERROR, "参数异常");
        }

        if (!multipartFile.isEmpty()) {
            try {

                InputStream inputStream = multipartFile.getInputStream();
                String path = fileService.uploadFile(inputStream, CommonUtil.getFileName());

                DiaryResource diaryResource = new DiaryResource();
                diaryResource.setDiaryId(Integer.valueOf(diaryId));
                diaryResource.setCreateTime(new Date());
                diaryResource.setResourceUrl(path);
                diaryResource.setType(Integer.valueOf(resourceType));

                diaryResourceApi.add(diaryResource);

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


    /**
     * 评论
     *
     * @param diaryCommentModel
     * @return
     */
    @PostMapping("/comment")
    public Object comment(@RequestBody DiaryCommentModel diaryCommentModel) {

        PunchCardDiary cardDiary = punchCardDiaryApi.getById(diaryCommentModel.getDiaryId());
        if (Objects.isNull(cardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        DiaryComment diaryComment = new DiaryComment();

        BeanUtils.copyProperties(diaryCommentModel, diaryComment);

        diaryCommentApi.add(diaryComment);

        // 更新日记评论的数量
        PunchCardDiary punchCardDiary = new PunchCardDiary();
        punchCardDiary.setId(diaryCommentModel.getDiaryId());
        punchCardDiary.setCommentNum(cardDiary.getCommentNum() + 1);

        punchCardDiaryService.updateComment(punchCardDiary);

        return new ResponseModel();
    }

}
