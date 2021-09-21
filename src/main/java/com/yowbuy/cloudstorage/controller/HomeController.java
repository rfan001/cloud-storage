package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.Credential;
import com.yowbuy.cloudstorage.model.Note;
import com.yowbuy.cloudstorage.model.UploadedFile;
import com.yowbuy.cloudstorage.model.User;
import com.yowbuy.cloudstorage.sevice.CredentialService;
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
    public final CredentialService credentialService;

    public HomeController(FileService fileService, NoteService noteService, UserService userService, CredentialService credentialService) {
        this.fileService = fileService;
        this.noteService = noteService;
        this.userService = userService;
        this.credentialService = credentialService;
    }

    @GetMapping
    public String visitHome(Credential credential, Note note, Model model, Authentication authentication){
        User user = userService.getUser(authentication.getName());
        if(user == null){
            return "login";
        }
        List<UploadedFile> fileList= fileService.getFiles();
        List<Note> noteList = noteService.getNotes(user.getUserid());
        List<Credential> credentials= credentialService.getCredentialList(authentication.getName());

        model.addAttribute("fileList", fileList);
        model.addAttribute("noteList",noteList);
        model.addAttribute("credentialList", credentials);
        return "home";
    }
}
