package com.zyrj.usermanagement.dao;

import com.zyrj.usermanagement.domain.Article;
import com.zyrj.usermanagement.domain.ArticleContent;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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

    Article findArticleById(Integer id);

    List<Article> findAllArticleByCateId(Integer cateId);

    List<Article> findAllArticleLike(@Param("str")String like);

    List<Article> findArticleByAuLike(@Param("au")String auther, @Param("str")String like);

    List<Article> findArticleByAuId(@Param("au")String auther, @Param("ca")Integer cateId);
}
