package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.mapper.FileMapper;
import com.yowbuy.cloudstorage.sevice.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Controller
@RequestMapping("/uploadFile")
public class UploadController {
    private final FileService fileService;

    public UploadController(FileService fileService) {
        this.fileService = fileService;
    }

    @PostMapping
    public String uploadFiles(@RequestParam("fileUpload")MultipartFile file, Authentication authentication) throws IOException {
        int row = fileService.uploadFile(file, authentication.getName());
        if(row > 0){
            System.out.println("success");
        } else {
            System.out.println("Fail");
        }
        return "home";
    }
}
