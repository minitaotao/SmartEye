package com.example.scringboot.controller;

import cn.hutool.core.util.StrUtil;
import com.example.scringboot.controller.dto.UserDTO;
import com.example.scringboot.entity.User;
import com.example.scringboot.mapper.UserMapper;
import com.example.scringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserMapper userMapper;
    @GetMapping
    public List<User> Index()
    {
        List<User> all=userMapper.findAll();
        return all;
    }
    @Autowired
    private UserService userService;
    @PostMapping
    public boolean save(@RequestBody User user)
    {
        return userService.saveUser(user);
    }
    @PostMapping("/login")
    public User login(@RequestBody User user){
        User user1=userService.login(user.getUsername(),user.getPassword());
        return user1;
    }
}
