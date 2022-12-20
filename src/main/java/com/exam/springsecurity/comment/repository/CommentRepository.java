package com.exam.springsecurity.comment.repository;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    List<Comment> getCommentsByParticleOrderByDateDesc(Integer particle);

    List<Comment> getCommentsByUsername(String username);


}