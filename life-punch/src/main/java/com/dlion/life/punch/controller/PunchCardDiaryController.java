package com.dlion.life.punch.controller;

import com.dlion.life.base.api.*;
import com.dlion.life.base.entity.*;
import com.dlion.life.common.annotation.LoginUser;
import com.dlion.life.common.bo.DiarySearchPo;
import com.dlion.life.common.bo.PunchCardDiarySearch;
import com.dlion.life.common.constant.CharacterConstant;
import com.dlion.life.common.constant.DatePattern;
import com.dlion.life.common.constant.ResultConstant;
import com.dlion.life.common.model.PunchCardDiaryModel;
import com.dlion.life.common.model.ResponseModel;
import com.dlion.life.common.utils.CommonUtil;
import com.dlion.life.common.utils.DateUtil;
import com.dlion.life.punch.model.DiaryDetailModel;
import com.dlion.life.punch.model.MyPunchCardDiaryModel;
import com.dlion.life.punch.service.*;
import com.dlion.life.punch.vo.DeleteDiaryVo;
import com.dlion.life.punch.vo.DiaryProjectInfoVo;
import com.qiniu.common.QiniuException;
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
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 打卡日记
 *
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

    @Autowired
    private UserService userService;

    /**
     * 打卡
     *
     * @param punchCardDiaryModel
     * @return
     */
    @PostMapping
    @Transactional(rollbackFor = Exception.class)
    public Object add(@RequestBody PunchCardDiaryModel punchCardDiaryModel) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(punchCardDiaryModel.getProjectId());
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        PunchCardDiary punchCardDiary = new PunchCardDiary();
        UserProjectRecord newUserProjectRecord = new UserProjectRecord();
        BeanUtils.copyProperties(punchCardDiaryModel, punchCardDiary);

        UserProjectRecord userProjectRecord = userProjectRecordApi.getByUserId(punchCardDiaryModel.getUserId(), punchCardDiaryModel.getProjectId());

        List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.listByTime(punchCardDiaryModel.getUserId(), punchCardDiaryModel.getProjectId(),
                DateUtil.getBeginOfToday(), DateUtil.getEndOfToday());
        if (CollectionUtils.isEmpty(punchCardDiaryList)) {
            // 如果今天没有打卡
            punchCardDiary.setCurrDiaryPunchCardDayNum(userProjectRecord.getAllPunchCardDayNum() + 1);
            newUserProjectRecord.setAllPunchCardDayNum(userProjectRecord.getAllPunchCardDayNum() + 1);
        } else {
            // 如果今天已经打卡
            punchCardDiary.setCurrDiaryPunchCardDayNum(userProjectRecord.getAllPunchCardDayNum());
        }

        if (0 == punchCardDiaryModel.getIsRepairDiary()) {
            punchCardDiary.setPunchCardTime(new Date());
            punchCardDiary.setRepairPunchCardTime(new Date());
        } else {
            punchCardDiary.setRepairPunchCardTime(new Date());
        }

        int id = punchCardDiaryApi.add(punchCardDiary);

        //update user_project_record
        newUserProjectRecord.setId(userProjectRecord.getId());
        newUserProjectRecord.setLastPunchCardTime(new Date());
        newUserProjectRecord.setAllPunchCardNum(userProjectRecord.getAllPunchCardNum() + 1);
        userProjectRecordApi.update(newUserProjectRecord);

        //update punch_card_num
        PunchCardProject newProject = new PunchCardProject();
        newProject.setId(punchCardDiaryModel.getProjectId());
        newProject.setTodayPunchCardNum(punchCardProject.getTodayPunchCardNum() + 1);
        newProject.setAllPunchCardNum(punchCardProject.getAllPunchCardNum() + 1);
        punchCardProjectApi.update(newProject);

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

    /**
     * 查询用户打卡日记列表
     *
     * @param punchCardDiarySearch 查询条件
     * @return
     */
    @GetMapping("/listUserPunchCardDiary")
    public Object listUserPunchCardDiary(@LoginUser User loginUser, PunchCardDiarySearch punchCardDiarySearch) {

        if (Objects.isNull(punchCardDiarySearch.getVisitedUserId())) {
            return new ResponseModel();
        }

        List<PunchCardDiary> punchCardDiaryList = punchCardDiaryApi.listByUserId(punchCardDiarySearch.getVisitedUserId(),
                punchCardDiarySearch.getPageNo(), punchCardDiarySearch.getDataNum(), punchCardDiarySearch.getIsDiaryCreator());

        List<MyPunchCardDiaryModel> modelList = punchCardDiaryList.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);
            myPunchCardDiaryModel.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm));

            //未登录的时候全是未点赞
            myPunchCardDiaryModel.setHaveLike(Objects.isNull(loginUser) ? false : punchCardDiaryService.hasLike(punchCardDiary.getId(), loginUser.getId()));

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(userService.getPublister(punchCardDiary.getUserId()));

            myPunchCardDiaryModel.setTenLikeInfo(diaryLikeService.listLikeInfo(punchCardDiary.getId()));

            myPunchCardDiaryModel.setAllCommentInfo(commentInfoService.listUserInfo(punchCardDiary));

            return myPunchCardDiaryModel;
        }).collect(Collectors.toList());

        return new ResponseModel(modelList);
    }

    /**
     * 圈子详情打卡日记列表ø
     *
     * @param loginUser 当前登录对象
     * @param projectId 圈子ID
     * @param pageNo    当前页
     * @param dataNum   数量
     * @return
     */
    @GetMapping("/getDiaryListByProjectId")
    public Object getDiaryListByProjectId(@LoginUser User loginUser, @RequestParam Integer projectId,
                                          @RequestParam Integer pageNo, @RequestParam Integer dataNum) {

        PunchCardProject punchCardProject = punchCardProjectApi.getById(projectId);
        if (Objects.isNull(punchCardProject)) {
            return new ResponseModel(ResultConstant.ERROR, "圈子不存在");
        }

        Integer visibleType = 1;
        if (Objects.nonNull(loginUser)) {
            visibleType = Objects.equals(punchCardProject.getCreatorId(), loginUser.getId()) ? null : 0;
        }

        DiarySearchPo diarySearchPo = new DiarySearchPo(null, null, visibleType, projectId, pageNo, dataNum);
        List<PunchCardDiary> punchCardDiaries = punchCardDiaryApi.list(diarySearchPo);

        List<MyPunchCardDiaryModel> modelList = punchCardDiaries.stream().map(punchCardDiary -> {

            MyPunchCardDiaryModel myPunchCardDiaryModel = new MyPunchCardDiaryModel();

            BeanUtils.copyProperties(punchCardDiary, myPunchCardDiaryModel);

            myPunchCardDiaryModel.setPunchCardTime(DateUtil.formatDate(punchCardDiary.getPunchCardTime(), DatePattern.YYYY_MM_DD_HH_mm));

            if (Objects.isNull(loginUser)) {
                myPunchCardDiaryModel.setHaveLike(false);
            } else {
                Boolean hasLike = punchCardDiaryService.hasLike(punchCardDiary.getId(), loginUser.getId());
                myPunchCardDiaryModel.setHaveLike(hasLike);
                if (hasLike) {
                    DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(punchCardDiary.getId(), loginUser.getId());
                    myPunchCardDiaryModel.setLikeRecordId(diaryLike.getId());
                }
            }

            myPunchCardDiaryModel.setPunchCardProject(punchCardProjectService.punchCardProject(punchCardDiary.getProjectId()));

            myPunchCardDiaryModel.setDiaryResource(diaryResourceService.listByDiaryId(punchCardDiary.getId()));

            myPunchCardDiaryModel.setPublisher(userService.getPublister(punchCardDiary.getUserId()));

            myPunchCardDiaryModel.setTenLikeInfo(diaryLikeService.listLikeInfo(punchCardDiary.getId()));

            myPunchCardDiaryModel.setAllCommentInfo(commentInfoService.listUserInfo(punchCardDiary));

            if (StringUtils.isEmpty(punchCardDiary.getPunchCardAddress())) {
                myPunchCardDiaryModel.setPunchCardAddress("银河系某个未知的神秘星球");
            }
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

            myPunchCardDiaryModel.setPublisher(userService.getPublister(punchCardDiary.getUserId()));

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
     * @return
     */
    @GetMapping("/getDiaryDetailInfoById")
    public Object getDiaryDetailInfoById(@LoginUser User loginUser, @RequestParam Integer diaryId) {

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

        if(Objects.isNull(loginUser)){
            diaryDetailModel.setHaveLike(false);
        }else {
            DiaryLike diaryLike = diaryLikeApi.getByDiaryIdAndUserId(diaryId, loginUser.getId());
            if (Objects.nonNull(diaryLike)) {
                diaryDetailModel.setLikeRecordId(diaryLike.getId());
                diaryDetailModel.setHaveLike(true);
            } else {
                diaryDetailModel.setHaveLike(false);
            }
        }
        if(Objects.isNull(loginUser)){
            diaryDetailModel.setExistAttendProject(false);
        }else {
            UserProjectRecord projectRecord = userProjectRecordApi.getByUserId(loginUser.getId(), punchCardProject.getId());
            if (Objects.nonNull(projectRecord)) {
                diaryDetailModel.setExistAttendProject(true);
            } else {
                diaryDetailModel.setExistAttendProject(false);
            }
        }
        diaryDetailModel.setDiaryResource(diaryResourceService.listByDiaryId(diaryId));

        diaryDetailModel.setPublisher(userService.getPublister(punchCardDiary.getUserId()));

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
     * 删除打卡日记
     *
     * @param deleteDiaryVo
     * @return
     */
    @DeleteMapping("/deleteDiaryById")
    @Transactional(rollbackFor = Exception.class)
    public Object deleteDiaryById(@RequestBody DeleteDiaryVo deleteDiaryVo) {

        PunchCardDiary cardDiary = punchCardDiaryApi.getById(deleteDiaryVo.getDiaryId());
        if (Objects.isNull(cardDiary)) {
            return new ResponseModel(ResultConstant.ERROR, "日记不存在");
        }

        if (Objects.equals(cardDiary.getUserId(), deleteDiaryVo.getUserId())) {
            punchCardDiaryApi.delete(deleteDiaryVo.getDiaryId());

            // 删除点赞记录
            List<DiaryLike> diaryLikeList = diaryLikeApi.listByDiaryId(deleteDiaryVo.getDiaryId());
            diaryLikeList.stream().forEach(e -> {
                diaryLikeApi.delete(e.getId());
            });

            //删除对应的文件资源
            List<DiaryResource> resourceList = diaryResourceApi.getByDiaryId(deleteDiaryVo.getDiaryId());
            resourceList.stream().forEach(e -> {
                diaryResourceApi.delete(e.getId());
                //删除文件
                String fileName = StringUtils.substringAfterLast(e.getResourceUrl(), CharacterConstant.URL_SPLIT_STR);
                try {
                    fileService.delete(fileName);
                } catch (QiniuException ex) {
                    logger.error("日记资源文件删除失败，diaryId：{}，filename：{}", deleteDiaryVo.getDiaryId(), fileName);
                }
            });
        }

        return new ResponseModel();
    }


}
