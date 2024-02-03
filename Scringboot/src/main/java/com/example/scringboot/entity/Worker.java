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
@TableName(value="workers")
public class Worker {
    @TableId(type = IdType.AUTO)
    private String 人员编号;
    private String 人员姓名;
    private String 违规操作频率;
    private String 考核情况;
    private String 所属车间;
    private String 最近一次违规操作;
    private String 违规操作时间;
}
