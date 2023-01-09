package com.exam.springsecurity.article.respository;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository articleRepository;

    @Test
    void findArticleByUrltoimage() {
        List<Article> articles = articleRepository.findArticleByUrltoimage("https://cdn.cnn.com/cnnnext/dam/assets/221229103925-02-ukraine-attack-122922-super-tease.jpg");
        assertThat(articles.size()).as("Get Articles By URL to Image").isEqualTo(1);


    }

    @Test
    void getArticlesByCategoryOrderByPublishedatDesc() {
        List<Article> articles = articleRepository.getArticlesByCategoryOrderByPublishedatDesc("general");
        assertThat(articles.size()).as("Get Articles By Category").isGreaterThan(0);
    }
}