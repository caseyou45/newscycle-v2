package com.exam.springsecurity.comment.controller;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@RequestMapping(path = "/api")
public class CommentController {


    private final CommentService commentService;


    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    /*  Route for saving one comment

      @param  the comment in the request body
      @return  the comment once saved

      */

    @PostMapping(path = "/comment")
    public @ResponseBody
    Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);

    }

    /*  Route for getting all comments by article

      @param  id of the article
      @return all comments associated with the article
      @Example request URI /api/comment/article?parent_article=1

      */

    @GetMapping(path = "/comment/article")
    public @ResponseBody
    List<Comment> getCommentsByParentArticle(@RequestParam Integer parent_article) {
        return commentService.getCommentsByParentArticle(parent_article);
    }


    /*  Route for editing one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once updated
    @Example request URI /api/comment/edit?comment_id=1

  */
    @PatchMapping(path = "/comment/edit")
    public @ResponseBody
    ResponseEntity<Comment> updateCommentByID(@RequestParam Integer comment_id, @RequestBody Comment comment) {
        try {
            Comment returnedComment = commentService.updateCommentByID(comment_id, comment);
            return new ResponseEntity<>(returnedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*  Route for deleting one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once 'deleted.' See method deleteCommentByID for details.
    @Example request URI /api/comment/delete?comment_id=1


     */

    @PatchMapping(path = "/comment/delete")
    public @ResponseBody
    ResponseEntity<?> deleteCommentByID(@RequestParam Integer comment_id, @RequestBody Comment comment) {
        try {
            Comment returnedComment = commentService.deleteCommentByID(comment_id);
            return new ResponseEntity<>(returnedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


     /*  Route for getting all comments made by user
      @param  id of the user
      @return all comments associated with the user
      @Example request URI /api/comment/user?username=fooname

      */

    @GetMapping(path = "/comment/user")
    public @ResponseBody
    List<Comment> getCommentsMadeByUser(@RequestParam String username) {
        return commentService.getCommentsByUsername(username);
    }


   /*  Route for getting one comment by id.
      @param  id of the comment
      @return the comment
      @Example request URI /api/comment?comment_id=1

      */

    @GetMapping(path = "/comment")
    public @ResponseBody
    ResponseEntity<Comment> getCommentByID(@RequestParam Integer comment_id) {
        try {
            Comment comment = commentService.getCommentByID(comment_id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
