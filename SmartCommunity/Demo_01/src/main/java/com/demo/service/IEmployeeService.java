package com.demo.service;

import com.demo.base.IBaseService;
import com.demo.domain.Employee;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IEmployeeService extends IBaseService<Employee> {

    Employee queryOne(String phone);

    //换绑手机
    void changePhone(Employee employee);
    /**
     * 用户更换头像
     * @param id  用户id
     * @param img 头像文件
     * @return 更换成功返回true否则返回fales
     */
    boolean updateHead(int id, MultipartFile img, HttpServletRequest request);
    /*
     * 查找所有执行层人员
     * */
    List<Employee> findQiuEr();
    /*
     * 查找所有管理层人员
     * */
    List<Employee> findManager();
}
