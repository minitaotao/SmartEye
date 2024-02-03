package com.example.scringboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TableName(value="video")
public class Video {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String fileName;
    private String filePath;
    public Video(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }
}
