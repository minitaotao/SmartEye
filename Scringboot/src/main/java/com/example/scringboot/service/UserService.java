package com.example.scringboot.service;

import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scringboot.controller.dto.UserDTO;
import com.example.scringboot.entity.User;
import com.example.scringboot.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService extends ServiceImpl<UserMapper,User> {

    @Resource
    private UserMapper userMapper;

    public boolean saveUser(User user) {

        if(userMapper.selectByUsername(user.getUsername())==null)
            return false;
        return saveOrUpdate(user);
    }

    public User login(String uname,String password){
        User user=userMapper.selectByUsername(uname);
        if(user!=null&&user.getPassword().equals(password)){
            return user;
        }
        return null;
    }

}


//    public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService{
//        @
//    }
//    @Autowired
//    private UserMapper userMapper;
//    public Integer save(User user){
//        if(user.getId()==null){
//            return userMapper.insert(user);
//        }else{
//            return userMapper.update(user);
//        }
//    }


