package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.ArticleMapper;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @auther ccm
 * @date 9:55
 */

@Service
public class ArticleService {

    @Autowired
    ArticleMapper articleMapper;




    public void saveArticle(Article article, ArticleContent articleContent) {
        articleMapper.saveArticle(article);
        articleContent.setArticleId(article.getArticleId());
        articleMapper.saveArticleContent(articleContent);
    }


    public List<Article> findArticle(String auther) {
        return articleMapper.findArticle(auther);
    }

    public List<Article> findAllArticle() {
        return articleMapper.findAllArticle();
    }

    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }
}
