package com.demo.service.impl;


import com.demo.base.BaseServiceImpl;
import com.demo.domain.Eventnotice;
import com.demo.mapper.EventnoticeMapper;
import com.demo.service.IEventnoticeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class EventnoticeServiceImpl extends BaseServiceImpl<Eventnotice> implements IEventnoticeService {

    @Resource
    private EventnoticeMapper eventnoticeMapper;

    @Override
    public List<Eventnotice> queryFor(Long id) {
        return eventnoticeMapper.queryFor(id);
    }

    @Override
    public void insertEvent(String event, Long id) {
        eventnoticeMapper.insertEvent(event, id);
    }

}
