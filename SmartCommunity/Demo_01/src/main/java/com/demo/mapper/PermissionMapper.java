package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Permission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface PermissionMapper extends BaseMapper<Permission> {

}