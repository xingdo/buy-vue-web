package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Employee;
import com.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {

    Employee queryOne(String phone);

    //换绑手机
    void changePhone(Employee employee);

    /**
     * 修改头像方法
     * @param id  用户id
     * @param img 新头像文件名
     * @return 操作成功返回1否则返回0
     */
    @Update("UPDATE `employee` SET `headimage`=#{img} WHERE (`id`=#{id})")
    int updateHead(@Param("id")int id, @Param("img")String img);


    /*
    * 查找所有执行层人员
    * */
    List<Employee> findQiuEr();
    /*
    * 查找所有管理层人员
    * */
    List<Employee> findManager();
}