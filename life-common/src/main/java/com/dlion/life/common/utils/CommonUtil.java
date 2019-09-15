package com.dlion.life.common.utils;

import com.dlion.life.common.constant.DatePattern;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

/**
 * @author 李正元
 * @date 2019/9/12
 */
public class CommonUtil {

    public static String getFilePath() {

        String filePath = LocalDateTime.now().format(DateTimeFormatter.ofPattern(DatePattern.YYYYMMDD));

        return filePath;
    }

    public static String getFileName() {

        String fileName = UUID.randomUUID().toString().replace("-", "");

        return fileName;
    }

}
