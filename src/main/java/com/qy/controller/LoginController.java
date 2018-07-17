package com.qy.controller;

import com.qy.base.core.Result;
import com.qy.base.core.ResultGenerator;
import com.qy.shiro.ShiroService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.LinkedHashMap;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    ShiroService shiroService;

    //首页
    @RequestMapping(value="index")
    public String index() {
        return "index";
    }

    //登录
    @RequestMapping(value="login")
    public String login() {
        return "login";
    }

    //权限测试用
    @RequestMapping(value="add")
    public String add() {
        return "add";
    }

    //未授权跳转的页面
    @RequestMapping(value="403")
    public String noPermissions() {
        return "403";
    }

    //更新权限
    @RequestMapping(value="updatePermission")
    @ResponseBody
    public String updatePermission() {
        shiroService.updatePermission();
        return "true";
    }

    //踢出用户
    @RequestMapping(value="kickouting")
    @ResponseBody
    public String kickouting() {

        return "kickout";
    }

    //被踢出后跳转的页面
    @RequestMapping(value="kickout")
    public String kickout() {
        return "kickout";
    }

    /**
     * ajax登录请求
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value="ajaxLogin",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> submitLogin(String username, String password, String vcode, Boolean rememberMe, Model model) {
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();

        if(!vcode.equals("1234")){
            resultMap.put("status", 500);
            resultMap.put("message", "验证码错误！");
            return resultMap;
        }

        try {
            UsernamePasswordToken token = new UsernamePasswordToken(username, password,rememberMe);
            SecurityUtils.getSubject().login(token);
            resultMap.put("status", 200);
            resultMap.put("message", "登录成功");

        } catch (Exception e) {
            resultMap.put("status", 500);
            resultMap.put("message", e.getMessage());
        }
        return resultMap;
    }

    /**
     * 退出
     * @return
     */
    @RequestMapping(value="logout",method =RequestMethod.GET)
    @ResponseBody
    public Map<String,Object> logout(){
        Map<String, Object> resultMap = new LinkedHashMap<String, Object>();
        try {
            //退出
            SecurityUtils.getSubject().logout();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return resultMap;
    }

    @RequestMapping(value = "testapi",method = RequestMethod.GET)
    public Result test(){
        return ResultGenerator.successResult("载荷");
    }

}
