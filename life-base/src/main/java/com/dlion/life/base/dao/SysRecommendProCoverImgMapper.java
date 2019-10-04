package com.dlion.life.base.dao;

import com.dlion.life.base.entity.SysRecommendProCoverImg;
import com.dlion.life.common.data.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysRecommendProCoverImgMapper extends BaseMapper<SysRecommendProCoverImg, Integer> {

    List<SysRecommendProCoverImg> list();

}