package com.example.scringboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.scringboot.entity.Abnormal;
import com.example.scringboot.mapper.AbnormalMapper;
import org.springframework.stereotype.Service;

@Service
public class AbnormalService extends ServiceImpl<AbnormalMapper, Abnormal> {
    public boolean saveChongsheng(Abnormal abnormal) {
        return saveOrUpdate(abnormal);
    }
}
