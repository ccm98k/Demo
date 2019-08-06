package com.zyrj.usermanagement.controller;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.*;
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
 * @date 10:56
 */

@Controller
@RequestMapping("Admin")
public class BlogAdminController {


    @Autowired
    ArticleService articleService;
    @Autowired
    CategoryService categoryService;
    @Autowired
    CommentService commentService;
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
    public Msg findArticle(@RequestParam(value = "pn",defaultValue = "1")Integer pn,@CookieValue("username") String auther,@RequestParam(value = "cateId",defaultValue = "0")Integer cateId,String like){



        if(cateId==0) {
            PageHelper.startPage(pn, 6);
            List<Article> articles = articleService.findArticle(auther);

            PageInfo page = new PageInfo(articles, 3);
            int maxpage = page.getPages();
            if (pn <= maxpage) {
                return new Msg().success().add("Articles", page);
            } else {
                PageHelper.startPage(maxpage, 6);
                articles = articleService.findArticle(auther);
                page = new PageInfo(articles, 3);
                return new Msg().success().add("Articles", page);
            }
        }else if(cateId==-1){
            //模糊查询
            PageHelper.startPage(pn, 6);
            List<Article> articles = articleService.findArticleByAuLike(auther,like);
            PageInfo page = new PageInfo(articles, 3);
            int maxpage = page.getPages();
            if (pn <= maxpage) {
                return new Msg().success().add("Articles", page);
            } else {
                PageHelper.startPage(maxpage, 6);
                articles = articleService.findArticleByAuLike(auther,like);
                page = new PageInfo(articles, 3);
                return new Msg().success().add("Articles", page);
            }
        }else{
            //分类查询
            PageHelper.startPage(pn, 6);
            List<Article> articles = articleService.findArticleByAuId(auther,cateId);

            PageInfo page = new PageInfo(articles, 3);
            int maxpage = page.getPages();
            if (pn <= maxpage) {
                return new Msg().success().add("Articles", page);
            } else {
                PageHelper.startPage(maxpage, 6);
                articles = articleService.findArticleByAuId(auther,cateId);
                page = new PageInfo(articles, 3);
                return new Msg().success().add("Articles", page);
            }


        }
    }















    @RequestMapping(value = "article/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg findArticleById(@PathVariable("id")Integer id){

        Article article= articleService.findArticleById(id);

        return new Msg().success().add("article",article);
    }

    @RequestMapping(value = "comments/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg findComments(@PathVariable("id") Integer id,@RequestParam(value = "pn",defaultValue = "1") Integer pn){
        PageHelper.startPage(pn,10);
        List<Comment> comments=commentService.findComments(id);
        PageInfo page=new PageInfo(comments,3);
        int maxpage=page.getPages();
        if(pn<=maxpage){
            return new Msg().success().add("comments",page);
        }else{
            PageHelper.startPage(maxpage,10);
            comments=commentService.findComments(id);
            page=new PageInfo(comments,3);
            return new Msg().success().add("comments",page);
        }

    }

    @RequestMapping(value = "comment/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public Msg deleteCommentById(@PathVariable("id") Integer id){
        commentService.deleteCommentById(id);
        return new Msg().success();
    }

    @RequestMapping(value = "comment/{id}",method = RequestMethod.PATCH)
    @ResponseBody
    public Msg updateCommentById(@PathVariable("id") Integer id){
        commentService.updateCommentById(id);
        return new Msg().success();
    }

    @RequestMapping(value = "comment/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Integer findFloorById(@PathVariable("id")Integer id,Integer article_id){


        return commentService.findFloorById(id,article_id);
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
        comment.setAgree(0);
        commentService.saveComment(comment);
        return new Msg().success();
    }



}
