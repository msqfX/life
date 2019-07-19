package com.dlion.life.base.api;

import com.dlion.life.base.entity.Note;
import com.dlion.life.base.fallback.UserApiFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@FeignClient(value = "life-base",fallback = UserApiFallback.class)
public interface NoteApi {

    @GetMapping("/listByOpenId")
    List<Note> listByOpenId(@RequestParam String openId);

    @PostMapping("/addNote")
    void addNote(@RequestBody Note note);

    @PutMapping
    void updateNote(@RequestBody Note note);

    @DeleteMapping
    void deleteNote(@RequestParam Integer id);

}


