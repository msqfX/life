package com.dlion.life.base.controller;

import com.dlion.life.base.api.NoteApi;
import com.dlion.life.base.entity.Note;
import com.dlion.life.base.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@RestController
public class NoteApiController implements NoteApi {

    @Autowired
    private NoteService noteService;

    @Override
    public List<Note> listByOpenId(String openId) {
        return noteService.listByOpenId(openId);
    }

    @Override
    public void addNote(@RequestBody Note note) {
        noteService.addNote(note);
    }

    @Override
    public void updateNote(@RequestBody Note note) {
        noteService.updateNote(note);
    }

    @Override
    public void deleteNote(Integer id) {
        noteService.deleteNote(id);
    }
}
