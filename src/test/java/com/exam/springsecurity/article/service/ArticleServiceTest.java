package com.exam.springsecurity.article.service;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.ParseException;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ArticleServiceTest {

    @Autowired
    private ArticleService articleService;


    @Test
    void getArticlesByCategory() {

        List<Article> articles = null;

        articles = articleService.getArticlesByCategory("general");

        Assertions.assertThat(articles.size()).as("Get Articles By Category").isGreaterThan(0);
    }

    @Test
    void getOneArticleByID() {


        Article article = articleService.getOneArticleByID(1);
        Assertions.assertThat(article).as("Get Article By ID").isNotNull();
    }
}