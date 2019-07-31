package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @auther ccm
 * @date 15:03
 */
@Mapper
@Repository
public interface CommentMapper {

    void saveComment(Comment comment);

}
