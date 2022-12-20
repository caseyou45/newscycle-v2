package com.exam.springsecurity.article.controller;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/api")

public class ArticleController {

    private ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    /*Route for returning a list of articles by category.
      @param  category of news
      @return articles by requested category
      */

    @GetMapping(path = "/article/category/{category}")
    public @ResponseBody
    List<Article> getArticlesByCategory(@PathVariable String category) throws ParseException {
        return articleService.getArticlesByCategory(category);
    }

    /*Route for returning one article by its id
      @param  id of a requested article
      @return requested article

     */

    @GetMapping(path = "/article/id/{id}")
    public @ResponseBody
    ResponseEntity<Article> getOneArticleByID(@PathVariable int id) {
        try {
            Article article = articleService.getOneArticleByID(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
