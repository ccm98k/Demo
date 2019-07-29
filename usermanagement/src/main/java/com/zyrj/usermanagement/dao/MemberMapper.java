package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ccm
 * @date 16:41
 */
@Mapper
@Repository
public interface MemberMapper {

    List<Member> selectAllMember();


}
