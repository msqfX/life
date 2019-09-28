package com.dlion.life.base.service;

import com.dlion.life.base.dao.PersonalPageVisitRecordMapper;
import com.dlion.life.base.entity.PersonalPageVisitRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/23
 */
@Service
public class PersonalPageVisitRecordService {

    @Autowired
    private PersonalPageVisitRecordMapper personalPageVisitRecordMapper;

    public List<PersonalPageVisitRecord> getByUserId(Integer visitedId){

       return personalPageVisitRecordMapper.getByUserId(visitedId);
    }



}
