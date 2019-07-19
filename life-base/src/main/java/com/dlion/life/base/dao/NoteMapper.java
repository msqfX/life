package com.dlion.life.base.dao;

import com.dlion.life.base.entity.Note;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@Mapper
public interface NoteMapper {

    void insertNote(@Param("note") Note note);

    Note selectById(@Param("id") Integer id);

    List<Note> listByUserId(@Param("userId") Integer userId);

    void update(@Param("note") Note note);

    void deleteNote(@Param("id") Integer id);
}
