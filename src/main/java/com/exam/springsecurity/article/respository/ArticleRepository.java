package com.exam.springsecurity.article.respository;

import com.exam.springsecurity.article.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {


    Article findArticleByUrltoimage(String urltoimage);

    List<Article> findArticlesByCategory(String category);


}
