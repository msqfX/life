package com.dlion.life.base.api;

import com.dlion.life.base.entity.Note;
import com.dlion.life.base.fallback.UserApiFallback;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 李正元
 * @date 2019/7/19
 */
@FeignClient(value = "life-base")
public interface NoteApi {

    @GetMapping("/life-base-note/listByUserId")
    List<Note> listByUserId(@RequestParam("userId") Integer userId);

    //@RequestLine("GET /getById")
    @GetMapping("/life-base-note/getById")
    Note getById(@RequestParam("id") Integer id);

    @PostMapping("/life-base-note/addNote")
    void addNote(@RequestBody Note note);

    @PutMapping("/life-base-note/updateNote")
    void updateNote(@RequestBody Note note);

    @DeleteMapping("/life-base-note/deleteNote")
    void deleteNote(@RequestParam("id") Integer id);

}


