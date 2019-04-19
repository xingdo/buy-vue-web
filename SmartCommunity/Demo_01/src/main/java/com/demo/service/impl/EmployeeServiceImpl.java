package com.demo.service.impl;


import com.demo.base.BaseServiceImpl;
import com.demo.domain.Employee;
import com.demo.mapper.EmployeeMapper;
import com.demo.service.IEmployeeService;
import com.demo.utils.FileUtil;
import com.demo.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class EmployeeServiceImpl extends BaseServiceImpl<Employee> implements IEmployeeService {

    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public Employee queryOne(String phone) {
        return employeeMapper.queryOne(phone);
    }

    @Override
    @Transactional
    public void changePhone(Employee employee) {
        employeeMapper.changePhone(employee);
    }

    @Override
    public void insert(Employee employee) {
        //新增保存修改
        if(employee.getId() == null){
            employee.setPassword(MD5Utils.createMD5Str(employee.getPassword()));
        }
        super.insert(employee);
    }

    @Override
    public void update(Employee employee) {
        if(employee.getId() != null){
            employee.setPassword(MD5Utils.createMD5Str(employee.getPassword()));
        }
        super.update(employee);
    }

    @Override
    @Transactional
    public boolean updateHead(int id, MultipartFile img, HttpServletRequest request) {
        try {
            return employeeMapper.updateHead(id, FileUtil.saveFile(img,request)) != 0;
        }catch (Exception e) {
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            return false;
        }
    }

    @Override
    public List<Employee> findQiuEr() {
        return employeeMapper.findQiuEr();
    }

    @Override
    public List<Employee> findManager() {
        return employeeMapper.findManager();
    }
}
