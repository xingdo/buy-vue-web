package com.demo.controller;

import com.demo.domain.Code;
import com.demo.domain.Employee;
import com.demo.service.ICodeService;
import com.demo.service.IEmployeeService;
import com.demo.utils.AjaxResult;
import com.demo.utils.PhoneNumUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collection;
import java.util.Date;

/*
* 验证接口
* */
@RestController
@RequestMapping("/smartCommunity")
public class RegisterController {
    @Autowired
    private IEmployeeService employeeService;


    @Resource
    private ICodeService codeService;
    /*
    发送短信验证码
   * */
    @RequestMapping(value = "/getNum",method = RequestMethod.POST)
    public AjaxResult phone(@RequestParam(value = "phone") String phone){
        String random = PhoneNumUtil.getRandom();
        //SendTelMsgUtils.sendMsgTo(phone,random);
        //将验证码存入数据库
        Code one = codeService.queryOne(phone);
        if (one==null){
            Code code = new Code();
            code.setPhone(phone);
            code.setRannum(random);
            codeService.insert(code);
        }else{
            //已经获取过的手机号直接修改验证码
            one.setRannum(random);
            one.setNowdate(new Date());
            codeService.update(one);
        }
        System.out.println(random);
        return AjaxResult.getAjaxResult().setKey(101).setCode(1).setData(random).setMsg("操作成功");
    }
    /*
     * 提交验证,,验证信息是什么?
     * */
    /*@RequestMapping(value = "/register",method = RequestMethod.POST)
    public AjaxResult phoneNum(@RequestBody Employee employee){
        if (!StringUtils.isEmpty(employee.getRannum())&&!StringUtils.isEmpty(employee.getPassword())&&
                !StringUtils.isEmpty(employee.getPolicecode())) {
            //判断生成的验证码，跟输入的验证码是否相同
            String code = codeService.findCode(employee.getPhone());
            if (code != null) {
                if (employee.getRannum().equals(code)) {
                    //判断手机号是否存在
                    Employee one = employeeService.queryOne(employee.getPhone());
                    if (one != null) {
                        return AjaxResult.getAjaxResult().setCode(0).setMsg("用户已存在");
                    } else {
                        employeeService.insert(employee);
                    }
                    return AjaxResult.getAjaxResult().setCode(1).setMsg("验证成功");
                } else {
                    return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码错误,请重新获取");
                }
            }   else {
                return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码失效,请重新获取");
                }
            } else {
            return AjaxResult.getAjaxResult().setCode(0).setMsg("请输入完整信息");
        }
    }*/
    /*
     * 注册并登录
     * */
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public AjaxResult registerLogin(@RequestParam(value = "phone") String phone,@RequestParam(value = "password") String password,
                                    @RequestParam(value = "rannum") String rannum,@RequestParam(value = "policecode") String policecode,
                                    @RequestParam(value = "username") String username,
                                    HttpServletRequest request, HttpServletResponse resp){
        if (!StringUtils.isEmpty(rannum)&&!StringUtils.isEmpty(password)&&
                !StringUtils.isEmpty(policecode)/*&&!StringUtils.isEmpty(username)*/){
            //判断生成的验证码，跟输入的验证码是否相同
            String code = codeService.findCode(phone);
            if (code != null) {
                if (rannum.equals(code)) {
                    //判断手机号是否存在
                    Employee one = employeeService.queryOne(phone);
                    if (one != null) {
                        return AjaxResult.getAjaxResult().setCode(0).setMsg("用户已存在");
                    } else {
                        try {
                            String pwd = password;
                            Employee employee = new Employee();
                            employee.setPolicecode(policecode);
                            employee.setPhone(phone);
                            employee.setPassword(password);
                            employee.setUsername(username);

                            System.out.println(employee);
                            employeeService.insert(employee);
                            Subject subject = SecurityUtils.getSubject();
                            UsernamePasswordToken token = new UsernamePasswordToken(phone,pwd);
                            subject.login(token);
                            //处理session
                            DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
                            DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
                            Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();
                            if (subject.isAuthenticated()) {
                                for (Session session : sessions) {
                                    //方法一、当第二次登录时，给出提示“用户已登录”，停留在登录页面
                                    if(employee.getPhone().equals(session.getAttribute("loginedUser"))){
                                        subject.logout();
                                        throw new RuntimeException("用户已登录");
                                    }
                                }
                            }
                            Employee loginuser = (Employee)subject.getPrincipal();
                            subject.getSession().setAttribute("loginedUser",phone);
                            return AjaxResult.getAjaxResult().setCode(1).setMsg("登录成功").setData(loginuser).setKey(102);
                        } catch (IncorrectCredentialsException ice) {
                            return AjaxResult.getAjaxResult().setMsg("密码错误").setCode(0).setKey(102);
                        } catch (UnknownAccountException uae) {
                            return AjaxResult.getAjaxResult().setMsg("账号不存在").setCode(0).setKey(102);
                        } catch (LockedAccountException e) {
                            return AjaxResult.getAjaxResult().setMsg("账号被锁定").setCode(0).setKey(102);
                        } catch (ExcessiveAttemptsException eae) {
                            return AjaxResult.getAjaxResult().setMsg("操作频繁，请稍后再试").setCode(0).setKey(102);
                        } catch (RuntimeException e){
                            return AjaxResult.getAjaxResult().setMsg(e.getMessage()).setCode(0).setKey(102);
                        }
                    }
                } else {
                    return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码错误,请重新获取").setKey(102);
                }
            }   else {
                return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码失效,请重新获取").setKey(102);
            }
        } else {
            return AjaxResult.getAjaxResult().setCode(0).setMsg("请输入完整信息").setKey(102);
        }
    }
    /*
     * 修改密码
     * */
    @RequestMapping(value = "/changePwd",method = RequestMethod.POST)
    public AjaxResult changePwd(@RequestParam(value = "phone") String phone,@RequestParam(value = "password") String password,
                                @RequestParam(value = "rannum") String rannum){
        if (!StringUtils.isEmpty(rannum)&&!StringUtils.isEmpty(password)&&!StringUtils.isEmpty(phone)) {
            //判断生成的验证码，跟输入的验证码是否相同
            String code = codeService.findCode(phone);
            if (code!=null){
            if (rannum.equals(code)) {
                Employee one = employeeService.queryOne(phone);
                if (one==null){
                    return AjaxResult.getAjaxResult().setCode(0).setMsg("该用户不存在").setKey(103);
                }else{
                    System.out.println(password);
                    one.setPassword(password);
                    employeeService.update(one);
                    return AjaxResult.getAjaxResult().setCode(1).setMsg("修改密码成功").setKey(103);
                }
            }else {
                return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码错误,请重新获取").setKey(103);
                }
            }else {
                return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码失效,请重新获取").setKey(103);
            }
        }else{
            return AjaxResult.getAjaxResult().setCode(0).setMsg("请输入完整信息").setKey(103);
        }
    }

    /*
    * 换绑手机接口
    * */
    @RequestMapping(value = "/changePhone",method = RequestMethod.POST)
    public AjaxResult changePhone(@RequestParam(value = "phone") String phone,@RequestParam(value = "id") Long id,
                                  @RequestParam(value = "rannum") String rannum){
        if (!StringUtils.isEmpty(phone)&&!StringUtils.isEmpty(id)&&!StringUtils.isEmpty(rannum)){
            //判断生成的验证码，跟输入的验证码是否相同
            String code = codeService.findCode(phone);
            if (code!=null){
                if (rannum.equals(code)){
            if (employeeService.selectOne(id)!=null){
                Employee employee = new Employee();
                employee.setPhone(phone);
                employee.setId(id);
                System.out.println(employee);
                employeeService.changePhone(employee);
                return AjaxResult.getAjaxResult().setCode(1).setMsg("换绑手机成功").setKey(104);
            }else{
                return AjaxResult.getAjaxResult().setCode(0).setMsg("该用户不存在").setKey(104);
            }
        }else { return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码错误,请重新获取").setKey(104);
                }
            }else {
                return AjaxResult.getAjaxResult().setCode(0).setMsg("验证码失效,请重新获取").setKey(104);
            }
        }
       return AjaxResult.getAjaxResult().setCode(0).setMsg("请输入完整信息").setKey(104);
    }
}
