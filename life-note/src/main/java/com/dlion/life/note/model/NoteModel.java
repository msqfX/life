package com.dlion.life.note.model;

import lombok.Data;
import org.omg.CORBA.INTERNAL;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Data
public class NoteModel {

    private Integer id;

    private Integer userId;

    private String content;

}
