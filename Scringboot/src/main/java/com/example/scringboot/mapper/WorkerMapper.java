package com.example.scringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.scringboot.entity.Abnormal;
import com.example.scringboot.entity.Worker;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WorkerMapper extends BaseMapper<Worker> {
    @Select("SELECT * from workers ")
    List<Worker> findAll();
    @Insert("INSERT INTO worker(人员编号,人员姓名,违规操作频率,考核情况,所属车间,最近一次违规操作,违规操作时间) VALUES (#{人员编号},#{人员姓名},#{违规操作频率},#{考核情况},#{所属车间},#{最近一次违规操作},#{违规操作时间});")
    int insert(Worker worker);
    int update(Worker worker);
    @Select("select *from workers where 人员编号 like concat('%',#{人员编号},'%') limit #{pageNum},#{pageSize} ")
    List<Worker> selectPage1(Integer pageNum, Integer pageSize,String 人员编号);
    @Select("select  count(*) from workers  where 人员编号 like concat('%',#{人员编号},'%')")
    Integer selectPageTotal1(String 人员编号);
}
