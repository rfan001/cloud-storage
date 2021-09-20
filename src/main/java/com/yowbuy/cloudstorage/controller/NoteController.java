package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.Note;
import com.yowbuy.cloudstorage.sevice.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class NoteController {
    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @PostMapping("/note")
    public String addNote(Note note, Authentication authentication){
        if(note.getNoteId()==null){
            noteService.addNote(note,authentication.getName());
        } else {
            noteService.editNote(note);
        }
        return "redirect:/home";
    }
    @GetMapping("/deleteNote")
    public String deleteNote(@RequestParam("id") Integer noteId){
        noteService.deleteNote(noteId);
        return "redirect:/home";
    }
}
