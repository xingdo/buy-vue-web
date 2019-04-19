package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Eventnotice;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventnoticeMapper extends BaseMapper<Eventnotice> {

    //获得管理类型对应的任务事件类型接口
    List<Eventnotice> queryFor(Long id);

    //自定义事件类型接口
    void insertEvent(@Param("event")String event, @Param("id")Long id);

}