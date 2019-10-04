package com.dlion.life.base.api;

import com.dlion.life.base.entity.SysRecommendProCoverImg;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/10/3
 */
@FeignClient(value = "life-base")
public interface SysRecommendProCoverImgApi {

    /**
     * 查询系统推荐的圈子背景图片
     *
     * @return
     */
    @GetMapping("/sysRecommendProCoverImgApi/list")
    List<SysRecommendProCoverImg> list();

}
