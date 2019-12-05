package com.baizhi.cmfz.controller;

import com.baizhi.cmfz.entity.Admin;
import com.baizhi.cmfz.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @ResponseBody
    @RequestMapping("login")
    public String login(String username, String password, String code, HttpServletRequest request){
        String securityCode =(String) request.getSession().getAttribute("securityCode");
        if (securityCode.equals(code)) {
            Admin admin = adminService.login(username, password);
            if (admin == null) {
                return "账号或密码错误";
            } else {
                return null;
            }
        }else{
            return "验证码错误";
        }
    }
}
