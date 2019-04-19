package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Code;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface CodeMapper extends BaseMapper<Code> {

    //查询当前手机有没有发送过验证码
    Code queryOne(String phone);

    //获取验证码，根据时间
    String findCode(String phone);

}