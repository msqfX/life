package com.dlion.life.note.controller;

import com.dlion.life.base.api.NoteApi;
import com.dlion.life.base.entity.Note;
import com.dlion.life.note.model.NoteModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 李正元
 * @date 2019-07-16
 */
@RestController("/note")
public class NoteController {

    @Autowired
    private NoteApi noteApi;

    @GetMapping("/test")
    public String test(){
        return "succes";
    }

    @PostMapping
    public Object addNote(@RequestBody NoteModel noteModel){

        Note note = new Note();
        BeanUtils.copyProperties(noteModel,note);

        noteApi.addNote(note);

        return "success";
    }


    @PutMapping("{id}")
    public Object update(@PathVariable Integer id,@RequestBody NoteModel noteModel){

        Note note = new Note();
        BeanUtils.copyProperties(noteModel,note);

        noteApi.updateNote(note);

        return "success";
    }

    @DeleteMapping("{id}")
    public Object delete(@PathVariable Integer id){

        noteApi.deleteNote(id);

        return "success";

    }

    @GetMapping
    public Object listByOpenId(@RequestParam String openId){

        return noteApi.listByOpenId(openId);

    }


}
