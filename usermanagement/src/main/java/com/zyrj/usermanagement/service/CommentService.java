package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.CommentMapper;
import com.zyrj.usermanagement.domain.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
