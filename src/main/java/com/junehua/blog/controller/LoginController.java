package com.junehua.blog.controller;

import com.junehua.blog.pojo.User;
import com.junehua.blog.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class LoginController {

    @Autowired
    IUserService userServiceImpl;

    @GetMapping//get请求方式
    public String loginPage(){
        return "admin/login";//跳转到登录页面
    }

    @PostMapping("/login")
    public String login(@RequestParam String username,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes attributes){
        User user=userServiceImpl.checkUser(username,password);
        //登录失败
        if(user==null){
            attributes.addFlashAttribute("message","用户名或者密码错误");
            return "redirect:/admin";
        }
        user.setPassword(null);//将密码设置为空，防止泄露
        session.setAttribute("user",user );
        return "admin/index";
    }

    @GetMapping("/logout")
    public String loginout(HttpSession session){
        session.removeAttribute("user");//清楚session内容
        return "redirect:/admin";
    }
}
