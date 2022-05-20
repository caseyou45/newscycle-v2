package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CommentService {


    @Autowired
    private CommentRepository commentRepository;


    public Comment addComment(Comment comment) {
        commentRepository.save(comment);
        return comment;

    }


    public Iterable<Comment> getAllComments() {
        return commentRepository.findAll();
    }


    public Iterable<Comment> getCommentsByParentArticle(Integer particle) {
        return commentRepository.getCommentsByParticleOrderByDateDesc(particle);
    }

    public Comment updateCommentByID(Integer comment_id, Comment newComment) {
        Comment oldComment = commentRepository.findById(comment_id).get();
        oldComment.setContent(newComment.getContent());
        commentRepository.save(oldComment);

        return oldComment;
    }


    public Comment deleteCommentByID(Integer comment_id, Comment commentDeleted) {
        Comment oldComment = commentRepository.findById(comment_id).get();
        oldComment.setContent(commentDeleted.getContent());
        oldComment.setUsername(commentDeleted.getUsername());
        oldComment.setAuthor(5);
        commentRepository.save(oldComment);

        return oldComment;
    }
}
