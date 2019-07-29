package com.zyrj.usermanagement.domain;

/**
 * @auther ccm
 * @date 14:28
 */
public class Article {

    private Integer articleId;
    private String auther;
    private String title;
    private String summary;
    private String createTime;
    private Integer categoryId;
    private Category category;
    private ArticleContent articleContent;
    @Override
    public String toString() {
        return "Article{" +
                "articleId=" + articleId +
                ", auther='" + auther + '\'' +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", createTime='" + createTime + '\'' +
                ", categoryId=" + categoryId +
                ", category=" + category +
                ", articleContent=" + articleContent +
                '}';
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
    }

    public String getAuther() {
        return auther;
    }

    public void setAuther(String auther) {
        this.auther = auther;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArticleContent getArticleContent() {
        return articleContent;
    }

    public void setArticleContent(ArticleContent articleContent) {
        this.articleContent = articleContent;
    }





}
