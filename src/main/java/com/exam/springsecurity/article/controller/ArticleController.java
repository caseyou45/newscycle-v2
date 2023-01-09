package com.exam.springsecurity.article.controller;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.article.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/api")
public class ArticleController {

    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }


    /*Route for returning a list of articles by category.
      @param  category of news
      @return articles by requested category
      @Example request URI /api/article/category?category=general

      */

    @GetMapping(path = "/article/category")
    public @ResponseBody
    List<Article> getArticlesByCategory(@RequestParam String category) throws ParseException {
        return articleService.getArticlesByCategory(category);
    }

    /*Route for returning one article by its id
      @param  id of a requested article
      @return requested article
      @Example request URI /api/article/id?id=1

     */

    @GetMapping(path = "/article/id")
    public @ResponseBody
    ResponseEntity<Article> getOneArticleByID(@RequestParam int id) {
        try {
            Article article = articleService.getOneArticleByID(id);
            return new ResponseEntity<>(article, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }


}
