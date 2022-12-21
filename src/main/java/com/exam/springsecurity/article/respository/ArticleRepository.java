package com.exam.springsecurity.article.respository;

import com.exam.springsecurity.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {


    List<Article> findArticleByUrltoimage(String urltoimage);


    List<Article> getArticlesByCategoryOrderByPublishedatDesc(String category);


}
