package com.demo.mapper;

import com.demo.base.BaseMapper;
import com.demo.domain.Feedback;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

}