package com.dlion.life.common.model;

import com.dlion.life.common.vo.ProjectInfo;
import com.dlion.life.common.vo.Publisher;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 打卡日记推荐
 *
 * @author 李正元
 * @date 2019/9/12
 */
@Data
public class PunchCardDiaryRecommendModel {

    private Integer id;

    private String textContent;

    private String punchCardTime;

    private String punchCardAddress;

    private Double addressLongitude;

    private Double addressLatitude;

    private Integer likeUserNum;

    private Integer commentNum;

    private boolean haveLike;

    private Integer likeRecordId;

    private List<RecentThreeAttendUserListModel> recentThreeAttendUserList;

    private ProjectInfo projectInfo;

    private List diaryResource;

    private Publisher publisher;

}
