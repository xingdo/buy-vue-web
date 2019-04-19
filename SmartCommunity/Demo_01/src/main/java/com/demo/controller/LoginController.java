package com.demo.controller;

import com.demo.domain.Employee;
import com.demo.service.IEmployeeService;
import com.demo.utils.AjaxResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;

@RestController
@RequestMapping("/smartCommunity")
public class LoginController {

    @Autowired
    private IEmployeeService employeeService;

    //验证码
    private String num;

    /*
     * 登录
     * */
    /*@RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult login(Employee employee){
        if (!StringUtils.isEmpty(employee.getPassword())&&!StringUtils.isEmpty(employee.getPhone())){
            //先判断用户名存不存在，在判断密码正确性
            Employee userOne = employeeService.queryOne(employee.getPhone());
            if (userOne==null){
                return AjaxResult.getAjaxResult().setLog(false).setMsg("用户名不存在");
            }else{
                if (userOne.getPassword().equals(employee.getPassword())){
                    UserContext.setUser(employee);
                    return AjaxResult.getAjaxResult().setLog(true).setMsg("登录成功").setObj(employee);
                }else{
                    return AjaxResult.getAjaxResult().setLog(false).setMsg("密码错误");
                }
            }
        }
        return new AjaxResult().setLog(false).setMsg("请输入用户");
    }*/
    /*
    * 登录接口
    * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public AjaxResult  doLogin(@RequestParam(value = "phone") String phone,@RequestParam(value = "password") String password, HttpServletRequest request, HttpServletResponse resp) {
        if (StringUtils.isEmpty(password)&&StringUtils.isEmpty(phone)){
            return new AjaxResult().setCode(0).setMsg("用户名或密码不能为空");
        }
        try {
            Subject subject = SecurityUtils.getSubject();
            UsernamePasswordToken token = new UsernamePasswordToken(phone,password);
            subject.login(token);
            //处理session
            /*DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
            if (subject.isAuthenticated()) {
                for (Session session : sessions) {
                    //方法一、当第二次登录时，给出提示“用户已登录”，停留在登录页面
                    if(phone.equals(session.getAttribute("loginedUser"))){
                        //subject.logout();
                        throw new RuntimeException("用户已登录");
                    }
                    //方法二、当第二次登录时，把第一个session剔除
//                    if(username.equals(session.getAttribute("loginedUser"))){
//                        session.setTimeout(0);
//                    }
                }
            }*/
            //subject.getSession().setTimeout(1000*60*1);
            Employee loginuser = (Employee)subject.getPrincipal();
            System.out.println(loginuser);
            subject.getSession().setAttribute("loginedUser",phone);
            return AjaxResult.getAjaxResult().setCode(1).setMsg("登录成功").setData(loginuser).setKey(100);
        } catch (IncorrectCredentialsException ice) {
            return AjaxResult.getAjaxResult().setMsg("密码错误").setCode(0).setKey(100);
        } catch (UnknownAccountException uae) {
            return AjaxResult.getAjaxResult().setMsg("账号不存在").setCode(0).setKey(100);
        } catch (LockedAccountException e) {
            return AjaxResult.getAjaxResult().setMsg("账号被锁定").setCode(0).setKey(100);
        } catch (ExcessiveAttemptsException eae) {
            return AjaxResult.getAjaxResult().setMsg("操作频繁，请稍后再试").setCode(0).setKey(100);
        } catch (RuntimeException e){
            return AjaxResult.getAjaxResult().setMsg(e.getMessage()).setCode(0).setKey(100);
        }
    }

    //登出方法
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public AjaxResult logout(){
        //登出
        try {
            Subject subject = SecurityUtils.getSubject();
            if (subject.isAuthenticated()) {
                System.out.println(subject.toString());
                subject.logout();
                return AjaxResult.getAjaxResult().setCode(1).setMsg("退出成功").setKey(105);
            }
                return AjaxResult.getAjaxResult().setCode(0).setMsg("退出失败").setKey(105);
        }catch (Exception e){
            return AjaxResult.getAjaxResult().setCode(0).setMsg("退出异常").setKey(105);
        }
    }
}
