package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.Note;
import com.yowbuy.cloudstorage.model.UploadedFile;
import com.yowbuy.cloudstorage.model.User;
import com.yowbuy.cloudstorage.sevice.FileService;
import com.yowbuy.cloudstorage.sevice.NoteService;
import com.yowbuy.cloudstorage.sevice.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;
    public final NoteService noteService;
    public final UserService userService;

    public HomeController(FileService fileService, NoteService noteService, UserService userService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService = userService;
    }

    @GetMapping
    public String visitHome(Note note, Model model, Authentication authentication){
        User user = userService.getUser(authentication.getName());
        if(user == null){
            return "login";
        }
        List<UploadedFile> fileList= fileService.getFiles();
        List<Note> noteList = noteService.getNotes(user.getUserid());
        model.addAttribute("fileList", fileList);
        model.addAttribute("noteList",noteList);
        return "home";
    }
}
