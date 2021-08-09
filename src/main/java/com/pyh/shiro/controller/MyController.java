package com.pyh.shiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MyController {

    @RequestMapping("/login")
    public String login(@RequestParam String username,@RequestParam String password)
    {
        UsernamePasswordToken usernamePasswordToken=new UsernamePasswordToken(username,password);
        Subject subject = SecurityUtils.getSubject();

        subject.login(usernamePasswordToken);
        Session session = subject.getSession();
        session.setAttribute("loginuser",usernamePasswordToken);
        return "suc";
    }

    @RequestMapping("/logout")
    public String logout()
    {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "index";
    }
}
