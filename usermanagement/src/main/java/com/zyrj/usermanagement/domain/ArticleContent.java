package com.zyrj.usermanagement.domain;

/**
 * @auther ccm
 * @date 14:28
 */
public class ArticleContent {
    private Integer contentId;

    private String content;

    private Integer articleId;

    @Override
    public String toString() {
        return "ArticleContent{" +
                "contentId=" + contentId +
                ", content='" + content + '\'' +
                ", articleId=" + articleId +
                '}';
    }

    public Integer getContentId() {
        return contentId;
    }

    public void setContentId(Integer contentId) {
        this.contentId = contentId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }
}
