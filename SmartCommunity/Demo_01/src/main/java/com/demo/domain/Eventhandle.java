package com.demo.domain;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Eventhandle {

    private Long id;

    //用于接口接收参数，各个id
    private Long eventnoticeid;
    private Long employeeid;
    private Long mainid;
    //发布任务接收的小时
    private Integer donehour;

    public Integer getDonehour() {
        return donehour;
    }

    public void setDonehour(Integer donehour) {
        this.donehour = donehour;
    }

    public Long getEventnoticeid() {
        return eventnoticeid;
    }

    public void setEventnoticeid(Long eventnoticeid) {
        this.eventnoticeid = eventnoticeid;
    }

    public Long getEmployeeid() {
        return employeeid;
    }

    public void setEmployeeid(Long employeeid) {
        this.employeeid = employeeid;
    }

    public Long getMainid() {
        return mainid;
    }

    public void setMainid(Long mainid) {
        this.mainid = mainid;
    }

    private Eventnotice eventnotice;
    private String photopath;
    private String subphotopath;

    private String subdescribe;

    private Employee employee;

    private Long statues;

    private Date createdate = new Date();
    private Date donetime;

    private String address;

    private String mydescribe;

    private Managetype managetype;

    //驳回的原因
    private String reject;

    public String getSubdescribe() {
        return subdescribe;
    }

    public void setSubdescribe(String subdescribe) {
        this.subdescribe = subdescribe;
    }

    public String getReject() {
        return reject;
    }

    public void setReject(String reject) {
        this.reject = reject;
    }

    public String getSubphotopath() {
        return subphotopath;
    }

    public void setSubphotopath(String subphotopath) {
        this.subphotopath = subphotopath;
    }

    public Managetype getManagetype() {
        return managetype;
    }

    public void setManagetype(Managetype managetype) {
        this.managetype = managetype;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Eventnotice getEventnotice() {
        return eventnotice;
    }

    public void setEventnotice(Eventnotice eventnotice) {
        this.eventnotice = eventnotice;
    }

    public String getPhotopath() {
        return photopath;
    }

    public void setPhotopath(String photopath) {
        this.photopath = photopath;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }


    @JsonFormat(pattern = "yyyy-MM-dd HH:hh:ss",timezone = "GMT+8")
    public Date getCreatedate() {
        return createdate;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:hh:ss")
    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }
    @JsonFormat(pattern = "yyyy-MM-dd HH:hh:ss",timezone = "GMT+8")
    public Date getDonetime() {
        return donetime;
    }
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:hh:ss")
    public void setDonetime(Date donetime) {
        this.donetime = donetime;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getStatues() {
        return statues;
    }

    public void setStatues(Long statues) {
        this.statues = statues;
    }

    public String getMydescribe() {
        return mydescribe;
    }

    public void setMydescribe(String mydescribe) {
        this.mydescribe = mydescribe;
    }

    @Override
    public String toString() {
        return "Eventhandle{" +
                "id=" + id +
                ", eventnotice=" + eventnotice +
                ", photopath='" + photopath + '\'' +
                ", subphotopath='" + subphotopath + '\'' +
                ", subdescribe='" + subdescribe + '\'' +
                ", employee=" + employee +
                ", status=" + statues +
                ", createdate=" + createdate +
                ", donetime=" + donetime +
                ", address='" + address + '\'' +
                ", describe='" + mydescribe + '\'' +
                ", managetype=" + managetype +
                ", reject='" + reject + '\'' +
                '}';
    }
}