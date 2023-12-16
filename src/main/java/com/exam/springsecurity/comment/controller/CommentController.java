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
      @Example request URI /api/comment/article?id=1

      */

    @GetMapping(path = "/comment/article")
    public @ResponseBody
    List<Comment> getCommentsByParentArticle(@RequestParam Integer id) {
        return commentService.getCommentsByParentArticle(id);
    }


    /*  Route for editing one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once updated
    @Example request URI /api/comment/edit

  */
    @PatchMapping(path = "/comment/edit")
    public @ResponseBody
    ResponseEntity<Comment> updateCommentByID(@RequestBody Comment comment) {
        try {
            Comment returnedComment = commentService.updateCommentByID(comment);
            return new ResponseEntity<>(returnedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    /*  Route for deleting one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once 'deleted.' See method deleteCommentByID for details.
    @Example request URI /api/comment/delete

     */

    @PatchMapping(path = "/comment/delete")
    public @ResponseBody
    ResponseEntity<?> deleteCommentByID(@RequestBody Comment comment) {
        try {
            Comment returnedComment = commentService.deleteCommentByID(comment.getId());
            return new ResponseEntity<>(returnedComment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


     /*  Route for getting all comments made by user
      @param  username of the user
      @return all comments associated with the user
      @Example request URI /api/comment/made/user?username=fooname

      */

    @GetMapping(path = "/comment/made/user")
    public @ResponseBody
    List<Comment> getCommentsMadeByUser(@RequestParam String username) {
        return commentService.getCommentsByUsername(username);
    }

    /*  Route for getting all comments liked by user
      @param  username of the user
      @return all comments liked by the user
      @Example request URI /api/comment/liked/user?username=fooname

      */

    @GetMapping(path = "/comment/liked/user")
    public @ResponseBody
    List<Comment> getCommentsLikedByUser(@RequestParam String username) {
        return commentService.getCommentsLikedByUsername(username);
    }




   /*  Route for getting one comment by id.
      @param  id of the comment
      @return the comment
      @Example request URI /api/comment?id=1

      */

    @GetMapping(path = "/comment")
    public @ResponseBody
    ResponseEntity<Comment> getCommentByID(@RequestParam Integer id) {
        try {
            Comment comment = commentService.getCommentByID(id);
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
