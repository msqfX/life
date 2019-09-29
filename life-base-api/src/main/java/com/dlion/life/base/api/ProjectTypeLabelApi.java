package com.dlion.life.base.api;

import com.dlion.life.base.entity.ProjectTypeLabel;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/13
 */
@FeignClient(value = "life-base")
public interface ProjectTypeLabelApi {

    @PostMapping("/life-base-projectTypeLabel/add")
    void add(@RequestBody ProjectTypeLabel projectTypeLabel);

    @PutMapping("/life-base-projectTypeLabel/update")
    void update(@RequestBody ProjectTypeLabel projectTypeLabel);

    @DeleteMapping("/life-base-projectTypeLabel/delete")
    void delete(@RequestParam("id") Integer id);

    @GetMapping("/life-base-projectTypeLabel/getById")
    ProjectTypeLabel getById(@RequestParam("id") Integer id);

    @GetMapping("/life-base-projectTypeLabel/listByType")
    List<ProjectTypeLabel> listByType(@RequestParam("type") Integer type);

    @GetMapping("/life-base-projectTypeLabel/listByParentLabelName")
    List<ProjectTypeLabel> listByParentLabelName(@RequestParam("parentLabelName") String parentLabelName);
}
