package com.example.scringboot.entity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@TableName(value="user")
public class User {
    @TableId(type = IdType.AUTO)
    private String username;
    private String password;
}
