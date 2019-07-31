package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ccm
 * @date 15:03
 */
@Mapper
@Repository
public interface CommentMapper {

    void saveComment(Comment comment);

    List<Comment> findComments(Integer id);

    void deleteCommentById(Integer id);

    void updateCommentById(Integer id);

    List<Comment> findFloorById(Integer id);

    Integer countCommentById(Integer id);
}
