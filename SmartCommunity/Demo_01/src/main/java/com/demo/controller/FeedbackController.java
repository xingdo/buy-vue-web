package com.demo.controller;


import com.demo.domain.Feedback;
import com.demo.query.FeedbackQuery;
import com.demo.service.IFeedbackService;
import com.demo.utils.AjaxResult;
import com.demo.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


/*
* 问题反馈的接口
* */
@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Resource
    private IFeedbackService feedbackService;

    /*
    * 高级查询
    * */
    @RequestMapping("/pagelist")
    public PageList<Feedback> pagelist(FeedbackQuery feedbackQuery) {
        return feedbackService.queryPage(feedbackQuery);
    }

    /**
     * 删除对象信息
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            feedbackService.delete(id);
            return AjaxResult.getAjaxResult();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.getAjaxResult().setMsg("删除对象失败！"+e.getMessage());
        }
    }

    /**
     * 保存和修改公用的
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    public AjaxResult saveOrUpdate(@RequestBody Feedback feedback) {
        if (feedback != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (feedback.getId() != null) {
                    //修改
                    feedbackService.update(feedback);
                } else {
                    //添加
                    feedbackService.insert(feedback);
                }
                return AjaxResult.getAjaxResult().setCode(1).setMsg("提交成功，谢谢反馈").setKey(158);
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.getAjaxResult().setCode(0).setMsg("提交失败！"+e.getMessage()).setKey(158);
            }
        }
        return null;
    }
    /**
     * 查看所有的信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public List<Feedback > list(){
            return feedbackService.selectAll();
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Feedback  get(@PathVariable("id")Long id)
            {
            return feedbackService.selectOne(id);
            }


}
