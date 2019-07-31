package com.zyrj.usermanagement.domain;

import javax.validation.constraints.NotBlank;
import java.util.Date;
import java.util.List;

/**
 * @auther ccm
 * @date 14:47
 */
public class Comment {


    private Integer floor;
    private Integer commentId;
    @NotBlank
    private String comment;
    private String create_by;
    private Integer commentator;
    private Integer article_id;
    private Integer parent_id;
    private Integer agree;
    private User user;
    public Integer getAgree() {
        return agree;
    }

    public void setAgree(Integer agree) {
        this.agree = agree;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", create_by='" + create_by + '\'' +
                ", commentator=" + commentator +
                ", article_id=" + article_id +
                ", parent_id=" + parent_id +
                ", agree=" + agree +
                ", user=" + user +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getCreate_by() {
        return create_by;
    }

    public void setCreate_by(String create_by) {
        this.create_by = create_by;
    }

    public Integer getCommentator() {
        return commentator;
    }

    public void setCommentator(Integer commentator) {
        this.commentator = commentator;
    }

    public Integer getArticle_id() {
        return article_id;
    }

    public void setArticle_id(Integer article_id) {
        this.article_id = article_id;
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }
}
