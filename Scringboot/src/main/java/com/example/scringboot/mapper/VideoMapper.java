package com.example.scringboot.mapper;

import com.example.scringboot.entity.Video;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface VideoMapper {
    @Insert("INSERT INTO video (fileName, filePath) VALUES (#{fileName}, #{filePath})")
    void insertVideo(Video video);
    @Select("SELECT * FROM video WHERE fileName = #{fileName} AND filePath = #{filePath}")
    Video findByFileNameAndFilePath(@Param("fileName") String fileName, @Param("filePath") String filePath);
}