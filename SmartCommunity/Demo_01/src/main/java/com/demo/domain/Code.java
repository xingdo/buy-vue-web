package com.demo.domain;

import java.util.Date;
/*
* 接收验证码
* */
public class Code {
    private Long id;

    private String phone;

    private String rannum;

    private Date nowdate = new Date();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getRannum() {
        return rannum;
    }

    public void setRannum(String rannum) {
        this.rannum = rannum == null ? null : rannum.trim();
    }

    public Date getNowdate() {
        return nowdate;
    }

    public void setNowdate(Date nowdate) {
        this.nowdate = nowdate;
    }
}