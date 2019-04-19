package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Upload;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface UploadMapper extends BaseMapper<Upload> {

    //执行层提交的图片保存
    void subPath(@Param("photopath") List<String> photopath, @Param("eventhandleid") Long eventhandleid);

}