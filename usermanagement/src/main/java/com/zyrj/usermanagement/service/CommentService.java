package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.CommentMapper;
import com.zyrj.usermanagement.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.List;

/**
 * @auther ccm
 * @date 15:00
 */
@Service
public class CommentService {
    @Autowired
    CommentMapper commentMapper;

    public void saveComment(Comment comment) {

        commentMapper.saveComment(comment);

    }

    public List<Comment> findComments(Integer id) {
        return commentMapper.findComments(id);
    }

    public void deleteCommentById(Integer id) {
        commentMapper.deleteCommentById(id);
    }

    public void updateCommentById(Integer id) {
        commentMapper.updateCommentById(id);
    }

    public Integer findFloorById(Integer integer, Integer id) {
        List<Comment> comments=commentMapper.findFloorById(id);
        Integer floors=commentMapper.countCommentById(id);
        for(Comment comment:comments){
            if(comment.getCommentId()==integer){
                return floors+1-comment.getFloor();
            }
        }
        return 0;
    }
}
