package com.demo.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Employee {
    private Long id;

    private String username;

    private String password;

    private String phone;

    private Long departmentid;

    public Long getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(Long departmentid) {
        this.departmentid = departmentid;
    }

    private Department department;

    private String policecode;
    private Date creatdate = new Date();
    @JsonFormat(pattern = "yyyy-MM-dd HH:hh:ss",timezone = "GMT+8")
    public Date getCreatdate() {
        return creatdate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:hh:ss")
    public void setCreatdate(Date creatdate) {
        this.creatdate = creatdate;
    }

    private String headimage;
    //验证码接收
    private String rannum;
    //重复密码接收
    private String repeat;

    public String getRepeat() {
        return repeat;
    }

    public void setRepeat(String repeat) {
        this.repeat = repeat;
    }

    private Long groupid = 0l;

    public Long getGroupid() {
        return groupid;
    }

    public void setGroupid(Long groupid) {
        this.groupid = groupid;
    }

    public String getRannum() {
        return rannum;
    }

    public void setRannum(String rannum) {
        this.rannum = rannum;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public String getPolicecode() {
        return policecode;
    }

    public void setPolicecode(String policecode) {
        this.policecode = policecode == null ? null : policecode.trim();
    }

    public String getHeadimage() {
        return headimage;
    }

    public void setHeadimage(String headimage) {
        this.headimage = headimage == null ? null : headimage.trim();
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", departmentid=" + departmentid +
                ", department=" + department +
                ", policecode='" + policecode + '\'' +
                ", creatdate=" + creatdate +
                ", headimage='" + headimage + '\'' +
                ", rannum='" + rannum + '\'' +
                ", repeat='" + repeat + '\'' +
                ", groupid=" + groupid +
                '}';
    }
}