package com.dlion.life.punch.component;

import com.dlion.life.base.api.PunchCardProjectApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 圈子相关定时任务
 *
 * @author 李正元
 * @date 2019/9/30
 */
@Component
public class PunchCardProjectTask {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private PunchCardProjectApi punchCardProjectApi;

    /**
     * 清零今日打卡次数，每天晚上十二点执行
     *
     * @Scheduled 注解为注册任务调度
     * cron 参数 ： cron 表达式: 执行的时间规则！
     */
    @Scheduled(cron = "0 0 0 * * ?")
    public void refreshTodayPunchCardNum() {

        try {
            punchCardProjectApi.refreshTodayPunchCardNum();
            logger.info("清零今日打卡次数定时任务执行成功");
        } catch (Exception e) {
            logger.error("清零今日打卡次数定时任务执行失败");
        }
    }

}
