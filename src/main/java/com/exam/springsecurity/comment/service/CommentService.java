package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class CommentService {


    private final CommentRepository commentRepository;

    private final VoteRepository voteRepository;

    private final UserRepository userRepository;


    @Autowired
    public CommentService(CommentRepository commentRepository, VoteRepository voteRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.voteRepository = voteRepository;
        this.userRepository = userRepository;
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

    public List<Comment> getCommentsByParentArticle(Integer particle) {
        return commentRepository.getCommentsByParticleOrderByDateDesc(particle);
    }


    /*    This method updates a comments content

      @param    the comment and the id of the comment
      @return   the updated comment

      */


    public Comment updateCommentByID(Comment newComment) {
        Comment oldComment = commentRepository.findById(newComment.getId()).get();

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

    public Comment deleteCommentByID(Integer comment_id) {
        Comment oldComment = commentRepository.findById(comment_id).get();
        oldComment.setContent("COMMENT DELETED");
        oldComment.setUsername("REMOVED");
        oldComment.setDeleted(true);
        commentRepository.save(oldComment);

        return oldComment;
    }

    public List<Comment> getCommentsByUsername(String username) {
        return commentRepository.getCommentsByUsername(username);

    }

    public Comment getCommentByID(Integer id) {
        return commentRepository.findById(id).get();

    }


    public List<Comment> getCommentsLikedByUsername(String username) {

        System.out.println(username);

        Users user = userRepository.getByUsername(username);

        List<Comment> comments = new ArrayList<>();

        List<Vote> likes = voteRepository.getVotesByAuthor(user.getId());

        for (Vote v : likes) {
            comments.add(commentRepository.findById(v.getCommentid()).get());
        }


        return comments;
    }


}
