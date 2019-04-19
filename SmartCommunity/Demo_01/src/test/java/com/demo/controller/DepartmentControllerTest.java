package com.demo.controller;

import com.demo.Application;
import com.demo.mapper.*;
import com.demo.service.IDepartmentService;
import com.demo.service.IEmployeeService;
import com.demo.service.IEventhandleService;
import com.demo.service.IFeedbackService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DepartmentControllerTest {
    @Autowired
    private IDepartmentService departmentService;
    @Resource
    private IEmployeeService employeeService;
    @Resource
    private EventhandleMapper eventhandleMapper;
    @Autowired
    private IEventhandleService eventhandleService;
    @Resource
    private EmployeeMapper employeeMapper;
    @Resource
    private UploadMapper uploadMapper;
    @Resource
    private ManagetypeMapper managetypeMapper;

    @Resource
    private EventnoticeMapper eventnotice;
    @Resource
    private FeedbackMapper feedbackMapper;
    @Resource
    private IFeedbackService feedbackService;
    @Test
    public void get() {
        System.out.println(departmentService.selectOne(1l));
    }

    @Test
    public void list() {
        System.out.println(employeeService.selectAll());
    }

    @Test
    public void testsss()throws Exception{
        System.out.println(eventhandleMapper.queryNow(1l));
    }


    @Test
    public void test222()throws Exception{
        System.out.println(eventhandleService.eventNow(1l));
    }
    @Test
    public void testasdf()throws Exception{
        System.out.println(employeeMapper.findQiuEr());

    }

    @Test
    public void test333()throws Exception{
        System.out.println(managetypeMapper.selectAll());
    }

    @Test
    public void test777()throws Exception{
        System.out.println(eventhandleMapper.eventAllIssue());
    }

    @Test
    public void test5555()throws Exception{
        System.out.println(feedbackService.selectAll());
    }
}