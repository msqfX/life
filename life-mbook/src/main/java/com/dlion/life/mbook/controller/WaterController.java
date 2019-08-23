package com.dlion.life.mbook.controller;

import com.dlion.life.common.model.ResponseModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 账户流水
 *
 * @author 李正元
 * @date 2019-08-04
 */

@RestController
@RequestMapping("/api/water")
public class WaterController {

    @GetMapping
    public Object getWater(@RequestParam String userId) {

        Map<String, Object> result = new HashMap<>();
        result.put("yearmonth", "2019年08月");
        result.put("sumin", new BigDecimal(0));
        result.put("sumout", new BigDecimal(0));
        result.put("jieyu", new BigDecimal(0));

        Map<String, Object> yusuan = new HashMap<>();

        yusuan.put("ishome", 1);
        yusuan.put("istag", 1);
        yusuan.put("baifenbi", "100.00");
        yusuan.put("jieyu", "1,500.00");

        List message = new ArrayList();

        result.put("yusuan", yusuan);

        result.put("message",message);

        return new ResponseModel(result);

    }


}
