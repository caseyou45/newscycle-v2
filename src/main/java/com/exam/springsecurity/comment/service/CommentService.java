package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;


    public String addComment(Comment comment) {
        commentRepository.save(comment);
        return "Saved";

    }


    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }


    public Iterable<Comment> getCommentsByParentArticle(Integer parent_article) {
        return commentRepository.getCommentsByParentArticle(parent_article);
    }

}
