package com.exam.springsecurity.article.respository;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {


    List<Article> findArticleByUrltoimage(String urltoimage);


    List<Article> getArticlesByCategoryOrderByPublishedatDesc(String category);

}
