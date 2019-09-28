package com.dlion.life.base.controller;

import com.dlion.life.base.api.PersonalPageVisitRecordApi;
import com.dlion.life.base.entity.PersonalPageVisitRecord;
import com.dlion.life.base.service.PersonalPageVisitRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/28
 */
@RestController
public class PersonalPageVisitRecordApiController implements PersonalPageVisitRecordApi {

    @Autowired
    private PersonalPageVisitRecordService personalPageVisitRecordService;

    @Override
    public List<PersonalPageVisitRecord> getByUserId(Integer visitedId) {

        return personalPageVisitRecordService.getByUserId(visitedId);
    }
}
