package com.example.scringboot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
@RequestMapping("/upload")
@RestController
public class FileUploadController {

    @Value("${upload.directory}") // 从配置文件中获取上传文件夹路径
    private String uploadDirectory;

    @PostMapping
    public String handleFileUpload(@RequestParam("file") MultipartFile file) {
        if (!file.isEmpty()) {
            try {
                String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                File destination = new File(uploadDirectory + File.separator + fileName);
                file.transferTo(destination);
                return "文件上传成功";
            } catch (IOException e) {
                e.printStackTrace();
                return "文件上传失败";
            }
        } else {
            return "请选择要上传的文件";
        }
    }
}
