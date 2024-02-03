package com.example.scringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scringboot.entity.Abnormal;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AbnormalMapper extends BaseMapper<Abnormal> {
    @Select("SELECT * from abnormal ")
    List<Abnormal> findAll();
    @Insert("INSERT INTO abnormal(id,time,place,kind,state) VALUES (#{id},#{time},#{place},#{kind},#{state});")
    int insert(Abnormal abnormal);
    int update(Abnormal abnormal);
    @Select("select *from abnormal where state like concat('%',#{state},'%') limit #{pageNum},#{pageSize} ")
    List<Abnormal> selectPage1(Integer pageNum, Integer pageSize,String state);
    @Select("select  count(*) from abnormal  where state like concat('%',#{state},'%')")
    Integer selectPageTotal1(String state);
}
