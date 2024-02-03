package com.example.scringboot.controller;

import com.example.scringboot.entity.Abnormal;
import com.example.scringboot.entity.Worker;
import com.example.scringboot.mapper.AbnormalMapper;
import com.example.scringboot.mapper.WorkerMapper;
import com.example.scringboot.service.AbnormalService;
import com.example.scringboot.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/worker")
public class WorkerController {
    @Autowired
    private WorkerMapper workerMapper;
    @Autowired
    private WorkerService workerService;
    @PostMapping
    public boolean save(@RequestBody Worker worker)
    {
        return workerService.saveWorker(worker);
    }
    @GetMapping
    public List<Worker> Index()
    {
        List<Worker> all= workerMapper.findAll();
        return all;
    }
    @GetMapping("/page")
    public <email> Map<String,Object> findPage(@RequestParam Integer pageNum,
                                               @RequestParam Integer pageSize,
                                               @RequestParam String 人员编号)
    {
        pageNum=(pageNum-1)*pageSize;
        List<Worker> data= workerMapper.selectPage1(pageNum,pageSize,人员编号);
        Integer total= workerMapper.selectPageTotal1(人员编号);
        Map<String,Object> res=new HashMap<>();
        res.put("data",data);
        res.put("total",total);
        return res;

    }



}
