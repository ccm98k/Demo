package com.zyrj.usermanagement.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zyrj.usermanagement.domain.Msg;
import com.zyrj.usermanagement.domain.User;
import com.zyrj.usermanagement.domain.sha1;
import com.zyrj.usermanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @auther ccm
 * @date 16:11
 */
@Controller
public class UserController {

    @Autowired
    UserService userService;



    @RequestMapping(value = "/users",method = RequestMethod.GET)
    @ResponseBody
    public Msg findAllUsers(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        PageHelper.startPage(pn,5);
        List<User> list=userService.findAll();
        PageInfo page=new PageInfo(list,5);
        int maxpage=page.getPages();
        if(pn<=maxpage){
            return new Msg().success().add("PageInfo",page);
        }else{
            PageHelper.startPage(maxpage,5);
            list=userService.findAll();
            page=new PageInfo(list,5);
            return new Msg().success().add("PageInfo",page);
        }
    }

    @RequestMapping(value = "/checkName",method = RequestMethod.GET)
    @ResponseBody
    public Msg checkName(@RequestParam("username")String username){
        String regx="^[a-zA-Z0-9_-]{6,16}$";
        if(!username.matches(regx))
        {
            return new Msg().fail().add("va_msg","用户名必须为6-16位字母或数字！！");
        }
        boolean b= userService.checkName(username);
        if(b){
            return new Msg().success();
        }else {
            return new Msg().fail().add("va_msg","用户名已存在");
        }
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    @ResponseBody
    public Msg addUser(@Valid User user, BindingResult result){
        if(result.hasErrors())
        {
            Map<String,Object> map=new HashMap<>();
            List<FieldError> errors=result.getFieldErrors();
            for (FieldError fieldError:errors){
                map.put(fieldError.getField(),fieldError.getDefaultMessage());
            }
            return new Msg().fail().add("errorFields",map);
        }else{
            //System.out.println(user);
            userService.savaUser(user);

            return new Msg().success();
        }
    }

    @RequestMapping(value = "user/{id}",method = RequestMethod.GET)
    @ResponseBody
    public Msg findUserById(@PathVariable("id")Integer id){

        User user= userService.findById(id);
        System.out.println(user);
        return new Msg().add("user",user);
    }


    @RequestMapping(value = "user/{userId}",method = RequestMethod.PUT)
    @ResponseBody
    public void updateUser(User user){
        userService.updateUser(user);
    }

    @RequestMapping(value = "user/{ids}",method = RequestMethod.DELETE)
    @ResponseBody
    public void deleteUserById(@PathVariable("ids") String ids){
        if(ids.contains("-")){
            String[] str_ids=ids.split("-");
            List<Integer> del_ids=new ArrayList<>();
            for (String i:str_ids){
                del_ids.add(Integer.parseInt(i));
            }
            userService.deleteUserByList(del_ids);
        }else {
            userService.deleteUserById(Integer.parseInt(ids));
        }

    }


/*
*
* 登陆
* */
    @RequestMapping(value = "/sign_in",method = RequestMethod.POST)
    @ResponseBody
    public Msg login(@Valid User user, HttpServletRequest request){

        String username=user.getUsername();
        User u= userService.findByusername(username);
        if(u==null){
            //用户不存在
            return new Msg().fail();
        }
        if(u.getPassword()==null){
            //首次登陆设置密码
            return new Msg().add("login","1");
        }
        if(!u.getPassword().equals(sha1.encode(user.getPassword()))){
            return new Msg().fail();
        }else{
            request.getSession().setAttribute("user",user);
            System.out.println("登陆成功");
            return new Msg().success().add("userInfo",u);
        }
    }


/*
*
* 首次登陆，设置密码
* */

    @RequestMapping(value = "/sign_in",method = RequestMethod.PUT)
    @ResponseBody
    public Msg setPassword(@Valid User user,@Valid String repassword){
        String regpassword="^[a-zA-Z0-9_-]{6,16}$";
        String password=user.getPassword();
        if (password.equals(repassword)&&password.matches(regpassword)){

            userService.savePassword(user);

            return new Msg().success().add("userInfo",user);
        }


        return new Msg().fail();
    }


    @RequestMapping(value = "/FogotcheckName",method = RequestMethod.GET)
    @ResponseBody
    public Msg FogotcheckName(@RequestParam("username")String username){
        boolean b= userService.checkName(username);
        if(b){
            return new Msg().fail();
        }else {
            return new Msg().success();
        }
    }




}
