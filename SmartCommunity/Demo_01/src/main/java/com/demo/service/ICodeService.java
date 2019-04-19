package com.demo.service;

import com.demo.base.IBaseService;
import com.demo.domain.Code;

public interface ICodeService extends IBaseService<Code> {

    //查询当前手机有没有发送过验证码
    Code queryOne(String phone);

    String findCode(String phone);
}
