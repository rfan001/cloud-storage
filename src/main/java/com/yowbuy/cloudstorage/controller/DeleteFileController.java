package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.sevice.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/deleteFile")
public class DeleteFileController {
    private final FileService fileService;

    public DeleteFileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    public String deleteFile(@RequestParam("id") Integer fileId){
        fileService.deleteFile(fileId);
        return "home";
    }
}
