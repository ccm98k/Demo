package com.zyrj.usermanagement.service;

import com.zyrj.usermanagement.dao.ArticleMapper;
import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.ArticleContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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

    @Cacheable(value = "Articles",key = "#p0")
    public List<Article> findArticle(String auther) {
        return articleMapper.findArticle(auther);
    }

    public List<Article> findAllArticle() {
        return articleMapper.findAllArticle();
    }

    @Cacheable(value = "ArticlesById",key = "#p0")
    public Article findArticleById(Integer id) {
        return articleMapper.findArticleById(id);
    }


    public List<Article> findAllArticleByCateId(Integer cateId) {
        return articleMapper.findAllArticleByCateId(cateId);
    }

    public List<Article> findAllArticleLike(String like) {
        return articleMapper.findAllArticleLike(like);
    }

    
    public List<Article> findArticleByAuLike(String auther, String like) {
        return articleMapper.findArticleByAuLike(auther,like);

    }

    public List<Article> findArticleByAuId(String auther, Integer cateId) {
        return articleMapper.findArticleByAuId(auther,cateId);
    }
}
