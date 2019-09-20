package com.dlion.life.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 时间处理工具类
 *
 * @author 李正元
 * @date 2019-09-20
 */

public class DateUtil {

    /**
     * 对时间进行了格式化
     *
     * @param date
     * @param nFmt
     * @return
     */
    public static String formatDate(Date date, String nFmt) {

        SimpleDateFormat fmtDate = new SimpleDateFormat();

        return fmtDate.format(date);
    }


}
