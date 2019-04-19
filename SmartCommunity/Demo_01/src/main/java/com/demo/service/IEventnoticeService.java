package com.demo.service;

import com.demo.base.IBaseService;
import com.demo.domain.Eventnotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IEventnoticeService extends IBaseService<Eventnotice> {

    //获得管理类型对应的任务类型接口
    List<Eventnotice> queryFor(Long id);

    //自定义事件类型接口
    void insertEvent(@Param("event")String event, @Param("id")Long id);
}
