package com.example.scringboot.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scringboot.entity.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    @Select("SELECT *from user")
    List<User> findAll();
    @Insert("INSERT INTO user(username,password) VALUES (#{username},#{password})")
    int insert(User user);
    int update(User user);
    @Select("select *from user where username like concat('%',#{username},'%') limit #{pageNum},#{pageSize}")
    List<User> selectPage(Integer pageNum, Integer pageSize, String username);
    @Select("select  count(*) from user where username like concat('%',#{username},'%')")
    Integer selectPageTotal(String username);
    @Select("select  * from user where username=#{username}")
    User selectByUsername(String username);
}
