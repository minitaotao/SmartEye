package com.example.scringboot.controller;

import com.example.scringboot.entity.Abnormal;
import com.example.scringboot.mapper.AbnormalMapper;
import com.example.scringboot.service.AbnormalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/abnormal")
public class AbnormalController {
    @Autowired
    private AbnormalMapper abnormalMapper;
    @Autowired
    private AbnormalService abnormalService;
    @PostMapping
    public boolean save(@RequestBody Abnormal abnormal)
    {
        return abnormalService.saveChongsheng(abnormal);
    }
    @GetMapping
    public List<Abnormal> Index()
    {
        List<Abnormal> all= abnormalMapper.findAll();
        return all;
    }
    @GetMapping("/page")
    public <email> Map<String,Object> findPage(@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize,
                                               @RequestParam String state)
    {
        pageNum=(pageNum-1)*pageSize;
        List<Abnormal> data= abnormalMapper.selectPage1(pageNum,pageSize,state);
        Integer total= abnormalMapper.selectPageTotal1(state);
        Map<String,Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }



}
