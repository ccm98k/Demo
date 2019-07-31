package com.zyrj.usermanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.Category;
import com.zyrj.usermanagement.domain.Comment;
import com.zyrj.usermanagement.domain.Msg;
import com.zyrj.usermanagement.service.ArticleService;
import com.zyrj.usermanagement.service.CategoryService;

import com.zyrj.usermanagement.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;


import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Autowired
    CommentService commentService;

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

    @RequestMapping(value = "article/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg findArticleById(@PathVariable("id")Integer id){

        Article article= articleService.findArticleById(id);

        return new Msg().success().add("article",article);
    }

    @RequestMapping(value = "comment",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveComment(@Valid Comment comment, BindingResult result){
        if(result.hasErrors()){
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors=result.getFieldErrors();
            for (FieldError fieldError:errors){
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new Msg().fail().add("errorFields",map);
        }
        Date date=new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = format.format(date);
        comment.setCreate_by(dateStr);
        commentService.saveComment(comment);
        return new Msg().success();
    }

}
