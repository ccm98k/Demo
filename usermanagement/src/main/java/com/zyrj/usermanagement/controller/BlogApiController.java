package com.zyrj.usermanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.Category;
import com.zyrj.usermanagement.domain.Msg;
import com.zyrj.usermanagement.service.ArticleService;
import com.zyrj.usermanagement.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @auther ccm
 * @date 14:35
 */
@Controller
@RequestMapping("api")
public class BlogApiController {
    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "article/list",method = RequestMethod.GET)
    @ResponseBody
    public Msg  findAllArticle(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,6);
        List<Article> articles = articleService.findAllArticle();

        PageInfo page=new PageInfo(articles,3);
        int maxpage=page.getPages();
        if(pn<=maxpage){
            return new Msg().success().add("Articles",page);
        }else{
            PageHelper.startPage(maxpage,6);
            articles=articleService.findAllArticle();
            page=new PageInfo(articles,3);
            return new Msg().success().add("Articles",page);
        }
    }

    @RequestMapping(value = "category",method = RequestMethod.GET)
    @ResponseBody
    public Msg findCategory(){
        List<Category> categories= categoryService.findCategory();
        return new Msg().success().add("categories",categories);
    }

}
