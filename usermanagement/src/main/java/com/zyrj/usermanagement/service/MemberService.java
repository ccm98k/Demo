package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.MemberMapper;
import com.zyrj.usermanagement.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ccm
 * @date 16:40
 */
@Service
public class MemberService {

    @Autowired
    MemberMapper memberMapper;
    public List<Member> selectAllMember(){
        return memberMapper.selectAllMember();
    }

}
