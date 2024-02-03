package com.example.scringboot.controller;

import com.example.scringboot.entity.Video;
import com.example.scringboot.mapper.VideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/video")
public class VideoController {

    private final VideoMapper videoMapper;

    @Autowired
    public VideoController(VideoMapper videoMapper) {
        this.videoMapper = videoMapper;
    }

    @GetMapping
    public List<Video> getVideoFiles() {
        String folderPath = "D:\\个人桌面\\SmartEye\\video"; // 指定文件夹的路径
        List<Video> videoFiles = new ArrayList<>();

        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile() && isVideoFile(file.getName())) {
                    String fileName = file.getName();
                    String filePath = file.getAbsolutePath();

                    // 检查数据库中是否已存在相同的记录
                    Video existingVideo = videoMapper.findByFileNameAndFilePath(fileName, filePath);

                    if (existingVideo == null) {
                        Video video = new Video(fileName, filePath);
                        videoFiles.add(video);
                        videoMapper.insertVideo(video); // 插入数据库
                    }
                }
            }
        }

        return videoFiles;
    }

    private boolean isVideoFile(String fileName) {
        // 根据文件名后缀判断是否为视频文件，你可以根据需要自定义判断逻辑
        String extension = getFileExtension(fileName);
        return extension.equalsIgnoreCase("mp4") ||
                extension.equalsIgnoreCase("mov") ||
                extension.equalsIgnoreCase("avi") ||
                extension.equalsIgnoreCase("mkv");
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            return fileName.substring(dotIndex + 1);
        }
        return "";
    }
    // 省略判断视频文件和辅助方法
}