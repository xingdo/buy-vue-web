package com.demo.service.impl;


import com.demo.base.BaseServiceImpl;
import com.demo.domain.Code;
import com.demo.mapper.CodeMapper;
import com.demo.service.ICodeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


@Service
@Transactional(propagation = Propagation.SUPPORTS,readOnly = true)
public class CodeServiceImpl extends BaseServiceImpl<Code> implements ICodeService {

    @Resource
    private CodeMapper codeMapper;

    @Override
    public Code queryOne(String phone) {
        return codeMapper.queryOne(phone);
    }

    @Override
    public String findCode(String phone) {
        return codeMapper.findCode(phone);
    }

}
