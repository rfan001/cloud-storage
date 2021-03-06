package com.yowbuy.cloudstorage.controller;

import com.yowbuy.cloudstorage.sevice.FileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

@Controller
@RequestMapping("/download")
public class DownloadController {
    private final FileService fileService;

    public DownloadController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping
    @ResponseBody
    public String download(@RequestParam("id")Integer fileId, HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
        return fileService.downFileByBlob(request, response, fileId);
    }
}
