package com.dlion.life.punch.model;

import com.dlion.life.common.vo.AttendUserInfo;
import com.dlion.life.common.vo.ProjectInfoDetail;
import lombok.Data;

import java.util.List;

/**
 * 圈子详情
 *
 * @author 李正元
 * @date 2019/9/14
 */
@Data
public class PunchCardDetailModel {

    private ProjectInfoDetail projectInfo;

    private List<AttendUserInfo> attendUserInfo;




}
