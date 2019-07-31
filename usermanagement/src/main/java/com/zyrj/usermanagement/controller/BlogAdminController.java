package com.zyrj.usermanagement.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.ArticleContent;
import com.zyrj.usermanagement.domain.Category;
import com.zyrj.usermanagement.domain.Msg;
import com.zyrj.usermanagement.service.ArticleService;
import com.zyrj.usermanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @auther ccm
 * @date 10:56
 */

@Controller
@RequestMapping("Admin")
public class BlogAdminController {


    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;


    @RequestMapping(value = "category",method = RequestMethod.GET)
    @ResponseBody
    public Msg findCategory(){
    List<Category> categories= categoryService.findCategory();
    return new Msg().success().add("categories",categories);

    }

    @RequestMapping(value = "article",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveArticle(@Valid Article article,@RequestParam("content") String text){

        ArticleContent articleContent=new ArticleContent();
        articleContent.setContent(text);

        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        article.setCreateTime(dateStr);

        articleService.saveArticle(article,articleContent);

        return new Msg().success();
    }

    @RequestMapping(value = "articles",method = RequestMethod.GET)
    @ResponseBody
    public Msg findArticle(@RequestParam(value = "pn",defaultValue = "1")Integer pn,@CookieValue("username") String auther){
        PageHelper.startPage(pn,6);
        List<Article> articles = articleService.findArticle(auther);

        PageInfo page=new PageInfo(articles,3);
        int maxpage=page.getPages();
        if(pn<=maxpage){
            return new Msg().success().add("Articles",page);
        }else{
            PageHelper.startPage(maxpage,6);
            articles=articleService.findArticle(auther);
            page=new PageInfo(articles,3);
            return new Msg().success().add("Articles",page);
        }
    }




}
