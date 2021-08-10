package com.pyh.shiro.controller;

import com.pyh.shiro.mapper.UserMapper;
import com.pyh.shiro.pojo.User;
import com.pyh.shiro.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class MybatisController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AsyncService asyncService;

    @RequestMapping("/add/{id}/{name}/{password}/{perms}")
    public String add(@PathVariable Integer id,
                      @PathVariable String name,
                      @PathVariable String password,
                      @PathVariable String perms)
    {
        User user=new User(id,name,password,perms);
        userMapper.add(user);
        return "redirect:/select";
    }

    @RequestMapping("/delete/{id}")
    public String delete(@PathVariable Integer id)
    {
        userMapper.delete(id);
        return "redirect:/select";
    }

    @RequestMapping("/update/{name}/{id}")
    public String update(@PathVariable String name,
                         @PathVariable Integer id)
    {
        userMapper.update(name, id);
        return "redirect:/select";
    }

    @RequestMapping("/select")
    public String select(Model model)
    {
        asyncService.delay();
        List<User> userList= userMapper.select();
        model.addAttribute("userList",userList);
        return "select";
    }

    @RequestMapping("/noauth")
    @ResponseBody
    public String noauth()
    {
        return "未经授权";
    }

}
