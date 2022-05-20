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

    @PostMapping(path = "/comment")
    public @ResponseBody
    Comment addComment(@RequestBody Comment comment) {
        return commentService.addComment(comment);

    }

    @GetMapping(path = "/comment")
    public @ResponseBody
    Iterable<Comment> getAllComments() {
        return commentService.getAllComments();
    }


    @GetMapping(path = "/comment/{parent_article}")
    public @ResponseBody
    Iterable<Comment> getCommentsByParentArticle(@PathVariable Integer parent_article) {
        return commentService.getCommentsByParentArticle(parent_article);
    }

    @PatchMapping(path = "/comment/edit/{comment_id}")
    public @ResponseBody
    Comment updateCommentByID(@PathVariable Integer comment_id, @RequestBody Comment comment) {

        return commentService.updateCommentByID(comment_id, comment);
    }

    @PatchMapping(path = "/comment/delete/{comment_id}")
    public @ResponseBody
    Comment deleteCommentByID(@PathVariable Integer comment_id, @RequestBody Comment comment) {

        return commentService.deleteCommentByID(comment_id, comment);
    }

}
