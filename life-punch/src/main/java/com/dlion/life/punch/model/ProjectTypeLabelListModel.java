package com.dlion.life.punch.model;

import com.dlion.life.base.entity.ProjectTypeLabel;
import lombok.Data;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/9/15
 */
@Data
public class ProjectTypeLabelListModel {

    private List<ProjectTypeLabel> parentLabel;

    private List<ProjectTypeLabel> childLabel;

}
