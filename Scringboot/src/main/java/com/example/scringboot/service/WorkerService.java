package com.example.scringboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scringboot.entity.Abnormal;
import com.example.scringboot.entity.Worker;
import com.example.scringboot.mapper.AbnormalMapper;
import com.example.scringboot.mapper.WorkerMapper;
import org.springframework.stereotype.Service;

@Service
public class WorkerService extends ServiceImpl<WorkerMapper, Worker> {
    public  boolean saveWorker(Worker worker) {
        return saveOrUpdate(worker);
    }
}
