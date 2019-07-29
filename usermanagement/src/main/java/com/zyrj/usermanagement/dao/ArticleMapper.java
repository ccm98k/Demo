package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.ArticleContent;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @auther ccm
 * @date 16:15
 */
@Mapper
@Repository
public interface ArticleMapper {

    void saveArticle(Article article);

    void saveArticleContent(ArticleContent articleContent);

    List<Article> findArticle(String auther);

    List<Article> findAllArticle();
}
