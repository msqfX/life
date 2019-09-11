package com.dlion.life.base.api;

import com.dlion.life.base.entity.PunchCardProject;
import com.dlion.life.base.fallback.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/11
 */
@FeignClient(name = "life-base-punchCardProject", url = "localhost:8081", fallback = UserApiFallback.class)
public interface PunchCardProjectApi {

    @PostMapping("/life-base-punchCardProject/add")
    void add(@RequestBody PunchCardProject punchCardProject);

    @PutMapping("/life-base-punchCardProject/update")
    void update(@RequestBody PunchCardProject punchCardProject);

    @DeleteMapping("/life-base-punchCardProject/deleteById")
    void deleteById(@RequestParam("id") Integer id);

    @GetMapping("/life-base-punchCardProject/getByUserId")
    List<PunchCardProject> getByUserId(@RequestParam("userId") Integer userId);

    @GetMapping("/life-base-punchCardProject/getById")
    PunchCardProject getById(@RequestParam("id") Integer id);

}
