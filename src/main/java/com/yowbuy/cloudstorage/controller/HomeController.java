package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.model.UploadedFile;
import com.yowbuy.cloudstorage.sevice.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {
    private final FileService fileService;

    public HomeController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String visitHome(Model model){
        List<UploadedFile> fileList= fileService.getFiles();
        model.addAttribute("fileList", fileList);
        return "home";
    }
}
