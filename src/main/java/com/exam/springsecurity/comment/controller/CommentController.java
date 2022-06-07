package com.exam.springsecurity.comment.controller;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")
public class CommentController {


    @Autowired
    private CommentService commentService;

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

      */

    @GetMapping(path = "/comment/{parent_article}")
    public @ResponseBody
    Iterable<Comment> getCommentsByParentArticle(@PathVariable Integer parent_article) {
        return commentService.getCommentsByParentArticle(parent_article);
    }


    /*  Route for editing one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once updated

  */
    @PatchMapping(path = "/comment/edit/{comment_id}")
    public @ResponseBody
    Comment updateCommentByID(@PathVariable Integer comment_id, @RequestBody Comment comment) {

        return commentService.updateCommentByID(comment_id, comment);
    }


    /*  Route for deleting one comment

    @param  the comment in the request body, the id of the comment as a path variable
    @return the comment once 'deleted.' See method deleteCommentByID for details.


     */

    @PatchMapping(path = "/comment/delete/{comment_id}")
    public @ResponseBody
    Comment deleteCommentByID(@PathVariable Integer comment_id, @RequestBody Comment comment) {

        return commentService.deleteCommentByID(comment_id, comment);
    }


     /*  Route for getting all comments made by user

      @param  id of the user
      @return all comments associated with the user

      */

    @GetMapping(path = "/comment/user/commented/{username}")
    public @ResponseBody
    Iterable<Comment> getCommentsMadeByUser(@PathVariable String username) {
        return commentService.getCommentsByUsername(username);
    }


   /*  Route for getting one comment by id.

      @param  id of the comment
      @return the comment

      */

    @GetMapping(path = "/comment/voted/{id}")
    public @ResponseBody
    Comment getCommentByID(@PathVariable Integer id) {
        return commentService.getCommentByID(id);
    }

}
