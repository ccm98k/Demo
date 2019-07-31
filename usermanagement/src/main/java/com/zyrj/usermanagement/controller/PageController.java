package com.zyrj.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @auther ccm
 * @date 16:33
 */
@Controller
public class PageController {
    @GetMapping("/user")
    public String userPage(){
        return "user";
    }


    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/Admin")
    public String adminPage(){ return "adminBlog";}

    @GetMapping("/index")
    public String indexPage(){ return "index"; }

    @GetMapping("api/article")
    public String articleDetail(){ return "article"; }

}
