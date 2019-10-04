package com.dlion.life.base.service;

import com.dlion.life.base.dao.SysRecommendProCoverImgMapper;
import com.dlion.life.base.entity.SysRecommendProCoverImg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/10/3
 */
@Service
public class SysRecommendProCoverImgService {

    @Autowired
    private SysRecommendProCoverImgMapper sysRecommendProCoverImgMapper;


    public List<SysRecommendProCoverImg> list(){

        return sysRecommendProCoverImgMapper.list();
    }


}
