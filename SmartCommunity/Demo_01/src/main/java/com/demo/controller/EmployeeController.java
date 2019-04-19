package com.demo.controller;


import com.demo.domain.Employee;
import com.demo.domain.Eventhandle;
import com.demo.query.EmployeeQuery;
import com.demo.service.IEmployeeService;
import com.demo.service.IEventhandleService;
import com.demo.utils.AjaxResult;
import com.demo.utils.PageList;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Resource
    private IEventhandleService eventhandleService;
    /*
    * 高级查询
    * */
    @RequestMapping("/pagelist")
    public PageList<Employee> pagelist(EmployeeQuery employeeQuery) {

        return employeeService.queryPage(employeeQuery);
    }

    /**
     * 删除对象信息
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public AjaxResult delete(@PathVariable("id") Long id) {
        try {
            employeeService.delete(id);
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
    public AjaxResult saveOrUpdate(Employee employee) {
        if (employee != null) {
            try {
                //判断是否有id,有就是修改,没有就是添加
                if (employee.getId() != null) {
                    //修改
                    employeeService.update(employee);
                } else {
                    //添加
                    employeeService.insert(employee);
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
            List<Employee> employees = employeeService.selectAll();
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(employees);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败");
        }

    }

    //获取用户
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Employee  get(@PathVariable("id")Long id) {
            return employeeService.selectOne(id);
    }

    /*
    * 警员详情的接口
    * */
    @RequestMapping(value = "/detail",method = RequestMethod.POST)
    public AjaxResult detail(@RequestParam("id") Long id){
        try {
            Employee employee = employeeService.selectOne(id);
            //正在执行的任务
            List<Eventhandle> list = eventhandleService.queryNow(id);
            //历史任务
            List<Eventhandle> history = eventhandleService.queryHistory(id);
            Map<String ,Object> map = new HashedMap();
            map.put("警员信息", employee);
            map.put("当前任务", list);
            map.put("历史任务", history);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setData(map).setKey(215);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(215);
        }
    }

    /**
     * 用户更换头像
     * @param id  用户id
     * @param img 更换头像的文件
     * @return 返回是否成功信息
     */
   @RequestMapping(value = "/changeHead",method = RequestMethod.POST)
    public AjaxResult updateHead(int id, MultipartFile img, HttpServletRequest request){
       try {
           employeeService.updateHead(id,img,request);
           return AjaxResult.getAjaxResult().setCode(1).setMsg("修改头像成功").setKey(106);
       }catch (Exception e){
           return AjaxResult.getAjaxResult().setCode(0).setMsg("修改头像成功").setKey(106);
       }
    }
    /*
     * 查找所有执行层人员
     * */
    @RequestMapping(value = "/findQiuEr",method = RequestMethod.POST)
    public AjaxResult findQiuEr(){
        try {
            List<Employee> qiuEr = employeeService.findQiuEr();
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setKey(217).setData(qiuEr);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(217);
        }
    }
    /*
     * 查找所有管理层人员
     * */
    @RequestMapping(value = "/findManager",method = RequestMethod.POST)
    public AjaxResult findManager(){
        try {
            List<Employee> manager = employeeService.findManager();
            return AjaxResult.getAjaxResult().setCode(1).setMsg("操作成功").setKey(218).setData(manager);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("操作失败").setKey(218);
        }
    }

}
