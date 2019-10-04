package com.dlion.life.punch.vo;

import com.dlion.life.punch.model.ProIntrDetailInfoModel;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author 李正元
 * @date 2019/10/4
 */
@Data
public class ProIntrBarchUpdateModel {

    @NotNull(message = "参数异常")
    private List<ProIntrDetailInfoModel> projectIntrInfo;
}
