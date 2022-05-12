package com.exam.springsecurity.article.controller;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")

public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping(path = "/article/category/{category}")
    public @ResponseBody
    List<Article> getArticlesByCategory(@PathVariable String category) throws ParseException {
        return articleService.getArticlesByCategory(category);
    }

    @GetMapping(path = "/article/id/{id}")
    public @ResponseBody
    Optional<Article> getOneArticleByID(@PathVariable int id) {
        return articleService.getOneArticleByID(id);
    }
}
