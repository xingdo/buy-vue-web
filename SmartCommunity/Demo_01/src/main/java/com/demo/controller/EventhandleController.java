package com.demo.controller;


import com.demo.domain.Eventhandle;
import com.demo.query.EventhandleQuery;
import com.demo.service.IEventhandleService;
import com.demo.utils.AjaxResult;
import com.demo.utils.PageList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/eventhandle")
public class EventhandleController {

    @Autowired
    private IEventhandleService eventhandleService;

    /*
    * 高级查询
    * */
    @RequestMapping("/pagelist")
    public PageList<Eventhandle> pagelist(EventhandleQuery eventhandleQuery) {

        return eventhandleService.queryPage(eventhandleQuery);
    }

    /**
     * 删除对象信息
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            eventhandleService.delete(id);
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
    public AjaxResult saveOrUpdate(Eventhandle eventhandle) {
        if (eventhandle != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (eventhandle.getId() != null) {
                    //修改
                    eventhandleService.update(eventhandle);
                } else {
                    //添加
                    eventhandleService.insert(eventhandle);
                }
                return AjaxResult.getAjaxResult().setCode(1).setMsg("保存对象成功");
            } catch (Exception e) {
                e.printStackTrace();
                return AjaxResult.getAjaxResult().setMsg("保存对象失败！"+e.getMessage());
            }
        }
        return null;
    }
    /**
     * 查看所有的信息
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public AjaxResult list(){
        try {
            List<Eventhandle> eventhandles = eventhandleService.selectAll();
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败");
        }
    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Eventhandle  get(@PathVariable("id")Long id) {
        return eventhandleService.selectOne(id);
    }

    //查找当前用户的所有历史
    @RequestMapping(value = "/queryAll",method = RequestMethod.POST)
    public AjaxResult queryAll(@RequestParam("id") Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryAll(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(159);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(159);
        }
    }
    /*
     * 查询完成的审核通过的历史任务
     * */
    @RequestMapping(value = "/queryHistory",method = RequestMethod.POST)
    public AjaxResult queryHistory(@RequestParam("id") Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryHistory(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(153);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(153);
        }
    }

    //查找当前用户需要完成的任务
    @RequestMapping(value = "/queryNow",method = RequestMethod.POST)
    public AjaxResult queryNow(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryNow(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(152);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(152);
        }
    }

    //查找当前用户最新需要执行的任务
    @RequestMapping(value = "/eventNow",method = RequestMethod.POST)
    public AjaxResult eventNow(@RequestParam("id")Long id){
        try {
            Eventhandle eventhandle = eventhandleService.eventNow(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandle).setKey(151);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(151);
        }
    }

    /*
     * 查询当天完成的任务
     * */
    @RequestMapping(value = "/queryToday",method = RequestMethod.POST)
    public AjaxResult queryToday(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryToday(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(154);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(154);
        }
    }
    /*
     * 查询以前第n天完成的任务
     * */
    @RequestMapping(value = "/queryPastDay",method = RequestMethod.POST)
    public AjaxResult queryPastDay(@RequestParam("id")Long id,@RequestParam("day")Integer day){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryPastDay(id, day);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(155);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(155);
        }
    }
    /*
     * 查询以前第n月完成的任务
     * */
    @RequestMapping(value = "/queryPastMonth",method = RequestMethod.POST)
    public AjaxResult queryPastMonth(@RequestParam("id")Long id,@RequestParam("month")Integer month){
        try {
            List<Eventhandle> eventhandles = eventhandleService.queryPastMonth(id, month);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(156);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(156);
        }
    }

    //任务提交
    @RequestMapping(value = "/submit",method = RequestMethod.POST)
    public AjaxResult submit(@RequestParam("id")Long id){
        if(!StringUtils.isEmpty(id)){
            eventhandleService.submit(id);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("提交成功");
        }else {
            return AjaxResult.getAjaxResult().setCode(0).setMsg("提交失败");
        }
    }

    /**
     * 任务提交方法
     * @param id         任务id
     * @return  返回一个AjaxResule封装类，会告诉你是否操作成功
     */
    /*@RequestMapping(value = "/submitEventhandle",method = RequestMethod.POST)
    public AjaxResult submitEventhandle(MultipartFile[] files,Long id,String subdescrib){
        System.out.println(files + "  " + id + "  " + subdescrib);
        boolean bool = eventhandleService.submitEventhandle(id,files,subdescrib);
        return AjaxResult.getAjaxResult().setCode(bool).setMsg(bool?"提交成功":"提交失败");
    }*/

    /*//任务提交包括，上传信息以及图片
    @RequestMapping(value = "/newsubmit",method = RequestMethod.POST)
    public AjaxResult newsubmit(Long id, String subdescrib){
        if(!StringUtils.isEmpty(id)){
            eventhandleService.newsubmit(id, subdescrib);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("提交成功");
        }else {
            return AjaxResult.getAjaxResult().setCode(0).setMsg("提交失败");
        }
    }*/
    //审核通过
    @RequestMapping(value = "/pass",method = RequestMethod.POST)
    public AjaxResult pass(@RequestParam("id")Long id){
        try {
            eventhandleService.pass(id);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("提交成功").setKey(203);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("提交失败").setKey(203);
        }
    }

    //审核不通过
    @RequestMapping(value = "/Reject",method = RequestMethod.POST)
    public AjaxResult reject(@RequestParam("id")Long id,@RequestParam("reject")String reject){
        try {
            eventhandleService.reject(id,reject);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("提交成功").setKey(204);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("提交失败").setKey(204);
        }
    }

    //查询所有待审的任务
    @RequestMapping(value = "/findWait",method = RequestMethod.POST)
    public AjaxResult findWait(@RequestParam("id") Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.findWait(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(205);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(205);
        }
    }
    //查询所有通过的任务
    @RequestMapping(value = "/findPass",method = RequestMethod.POST)
    public AjaxResult findPass(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.findPass(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(206);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(206);
        }
    }
    //查询所有审核未通过的任务
    @RequestMapping(value = "/findReject",method = RequestMethod.POST)
    public AjaxResult findReject(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.findReject(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(207);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(207);
        }
    }

    //管理层，任务页面
    //查询所有最新发布的任务
    @RequestMapping(value = "/listAll",method = RequestMethod.POST)
    public AjaxResult listAll(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.listAll();
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(208);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(208);
        }
    }
    //查询所有正在执行的任务
    @RequestMapping(value = "/listNow",method = RequestMethod.POST)
    public AjaxResult listNow(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.listNow();
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(209);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(209);
        }
    }
    //查询所有等待审核的任务
    @RequestMapping(value = "/listWait",method = RequestMethod.POST)
    public AjaxResult listWait(@RequestParam("id")Long id){
        try {
            List<Eventhandle> eventhandles = eventhandleService.listWait();
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(210);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(210);
        }
    }

    /*
     * 获取所有事件对应类型总数的接口
     * */
    @RequestMapping(value = "/queryType",method = RequestMethod.POST)
     public AjaxResult queryType(){
        try {
            Map<String, BigDecimal> type = eventhandleService.queryType();
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(type).setKey(211);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(211);
        }
    }

    /*
     * 获取所有事件对应类型总数的接口 (我的最新发布)
     * */
    @RequestMapping(value = "/typeNew",method = RequestMethod.POST)
    public AjaxResult typeNew(@RequestParam("id")Long id){
        try {
            Map<String, BigDecimal> typeNew = eventhandleService.typeNew(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(typeNew).setKey(212);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(212);
        }
    }
    /*
     * 获取所有事件对应类型总数的接口  (我的正在处理)
     * */
    @RequestMapping(value = "/typeNow",method = RequestMethod.POST)
    public AjaxResult typeNow(@RequestParam("id")Long id){
        try {
            Map<String, BigDecimal> typeNow = eventhandleService.typeNow(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(typeNow).setKey(213);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(213);
        }
    }
    /*
     * 获取所有事件对应类型总数的接口  (我的等待审核)
     * */
    @RequestMapping(value = "/typeWait",method = RequestMethod.POST)
    public AjaxResult typeWait(@RequestParam("id") Long id){
        try {
            Map<String, BigDecimal> typeWait = eventhandleService.typeWait(id);
            return  AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(typeWait).setKey(214);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(214);
        }
    }
	/**
     * 关注任务
     * @param depId 用户id
     * @param eveId 任务id
     * @return 返回是否成功信息
     */
    @RequestMapping(value = "/concernEventhandle",method = RequestMethod.POST)
    public AjaxResult concernEventhandle(Long depId,Long eveId){
        try {
            eventhandleService.concernEventhandle(depId,eveId);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功");
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败");
        }
    }

    /**
     * 取消关注
     * @param depId 用户id
     * @param eveId 任务id
     * @return 返回是否成功信息
     */
    @RequestMapping(value = "/deleteMyConcern",method = RequestMethod.GET)
    public AjaxResult deleteMyConcern(int depId,int eveId){
        try {
            eventhandleService.deleteMyConcern(depId,eveId);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功");
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败");
        }
    }



    public AjaxResult updatePhone(String phone,String newPhone){
        try {
            eventhandleService.updatePhone(phone,newPhone);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功");
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败");
        }
    }

    /*public AjaxResult postEventhandleDIY(Eventhandle eventhandle,MultipartFile[] files){
        boolean bool = eventhandleService.postEventhandleDIY(eventhandle,files);
        return AjaxResult.getAjaxResult().setCode(bool).setMsg(bool?"操作成功":"操作失败");
    }*/

    /*
    * 按照我的思想，事件页面的接口
    * */
    /*事件页面
     * 查找所有管理者发布的任务接口，正在执行
     * */
    @RequestMapping(value = "/eventAllIssue",method = RequestMethod.POST)
    public AjaxResult eventAllIssue(){
        try {
            List<Eventhandle> eventhandles = eventhandleService.eventAllIssue();
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(217);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(217);
        }
    }
    /*
     * 查找所有管理者发布的任务接口，已结完成
     *
     * */
    @RequestMapping(value = "/eventAllDonne",method = RequestMethod.POST)
    public AjaxResult eventAllDonne(){
        try {
            List<Eventhandle> eventhandles = eventhandleService.eventAllDonne();
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(eventhandles).setKey(218);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(218);
        }
    }
}
