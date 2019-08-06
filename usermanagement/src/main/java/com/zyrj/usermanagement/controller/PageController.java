package com.zyrj.usermanagement.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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

    @GetMapping("Admin/article")
    public String myArticleDetail(){ return "myArticle"; }

    @GetMapping("/exit")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message","您已成功退出系统");
        return "redirect:/login";
    }

    @GetMapping("Admin/exit")
    public String Adminlogout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message","您已成功退出系统");
        return "redirect:/login";
    }

    @GetMapping("api/exit")
    public String apilogout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("message","您已成功退出系统");
        return "redirect:/login";
    }

    @GetMapping("/forgotPassword")
    public String ForgotPassword(){ return "forgotPassword"; }

    @GetMapping("/join")
    public String createAccount(){ return "join"; }


}
