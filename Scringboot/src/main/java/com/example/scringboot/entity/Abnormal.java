package com.example.scringboot.entity;
import cn.hutool.core.date.DateTime;
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
@TableName(value="abnormal")
public class Abnormal {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String time;
    private String place;
    private String kind;
    private String state;
}
