package com.dlion.life.punch;

import com.dlion.life.punch.service.FileService;
import com.qiniu.common.QiniuException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 李正元
 * @date 2019/10/4
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FileTest {

    @Autowired
    private FileService fileService;

    @Test
    public void deleteFile() {

        String fileName = "f48734f82ad043d0b9c6d21cdf1ceb33";

        try {
            fileService.delete(fileName);
        } catch (QiniuException e) {
            e.printStackTrace();
        }

    }


}
