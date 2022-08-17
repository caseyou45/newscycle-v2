package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CommentService {


    private CommentRepository commentRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    /*    This method saves one comment to the database.
      @param   the comment
      @return  the saved comment
      */
    public Comment addComment(Comment comment) {
        commentRepository.save(comment);
        return comment;

    }


    /*    This method returned all comments associated with an article.
      @param  the id of the article
      @return all comments associated with that article

      */

    public Iterable<Comment> getCommentsByParentArticle(Integer particle) {
        return commentRepository.getCommentsByParticleOrderByDateDesc(particle);
    }


    /*    This method updates a comments content

      @param    the comment and the id of the comment
      @return   the updated comment

      */


    public Comment updateCommentByID(Integer comment_id, Comment newComment) {
        Comment oldComment = commentRepository.findById(comment_id).get();
        oldComment.setContent(newComment.getContent());
        commentRepository.save(oldComment);

        return oldComment;

    }


    /*    This method 'deletes' a comment. Because the comments are intended to be 'threaded' on the frontend,
          a comment can not be actually deleted. Instead, the author id is changed to a default (5), and the content and
          username are changed to 'removed'.
      @param  the id and the comment
      @return  the deleted (aka 'removed') comment
      */

    public Comment deleteCommentByID(Integer comment_id, Comment commentDeleted) {
        Comment oldComment = commentRepository.findById(comment_id).get();
        oldComment.setContent(commentDeleted.getContent());
        oldComment.setUsername(commentDeleted.getUsername());
        oldComment.setAuthor(5);
        commentRepository.save(oldComment);

        return oldComment;
    }

    public Iterable<Comment> getCommentsByUsername(String username) {
        return commentRepository.getCommentsByUsername(username);

    }

    public Comment getCommentByID(Integer id) {
        return commentRepository.getCommentByid(id);

    }


}
