package com.zyrj.usermanagement.controller;

import com.zyrj.usermanagement.domain.Member;
import com.zyrj.usermanagement.domain.Msg;
import com.zyrj.usermanagement.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @auther ccm
 * @date 16:38
 */

@Controller
public class MemberController {
@Autowired
MemberService memberService;

@RequestMapping(value = "/members",method = RequestMethod.GET)
@ResponseBody
public Msg findAllMembers(){
    List<Member> list=memberService.selectAllMember();
    return new Msg().success().add("members",list);

}



}
