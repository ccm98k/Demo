package com.zyrj.usermanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.*;
import com.zyrj.usermanagement.service.ArticleService;
import com.zyrj.usermanagement.service.CategoryService;

import com.zyrj.usermanagement.service.CommentService;
import com.zyrj.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
    @Autowired
    UserService userService;

    @Autowired
    RedisTemplate redisTemplate;


    @RequestMapping(value = "article/list",method = RequestMethod.GET)
    @ResponseBody
    public Msg  findAllArticle(@RequestParam(value = "pn",defaultValue = "1")Integer pn,@RequestParam(value = "cateId",defaultValue = "0")Integer cateId,String like){
        if(cateId==0){
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
        }else if(cateId==-1){
            PageHelper.startPage(pn,6);
            List<Article> articles = articleService.findAllArticleLike(like);

            PageInfo page=new PageInfo(articles,3);
            int maxpage=page.getPages();
            if(pn<=maxpage){
                return new Msg().success().add("Articles",page);
            }else{
                PageHelper.startPage(maxpage,6);
                articles=articleService.findAllArticleLike(like);
                page=new PageInfo(articles,3);
                return new Msg().success().add("Articles",page);
            }
        }else{
            PageHelper.startPage(pn,6);
            List<Article> articles = articleService.findAllArticleByCateId(cateId);

            PageInfo page=new PageInfo(articles,3);
            int maxpage=page.getPages();
            if(pn<=maxpage){
                return new Msg().success().add("Articles",page);
            }else{
                PageHelper.startPage(maxpage,6);
                articles=articleService.findAllArticleByCateId(cateId);
                page=new PageInfo(articles,3);
                return new Msg().success().add("Articles",page);
            }
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
        comment.setAgree(0);
        commentService.saveComment(comment);
        return new Msg().success();
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


    @RequestMapping(value = "user",method = RequestMethod.PUT)
    @ResponseBody
    public Msg updatePassword(User user){
        userService.savePassword(user);
        return new Msg().success();
    }
    @RequestMapping(value = "user",method = RequestMethod.POST)
    @ResponseBody
    public Msg register(User user){

        user.setGradeId(1);

        userService.register(user);
        return new Msg().success();
    }

}
