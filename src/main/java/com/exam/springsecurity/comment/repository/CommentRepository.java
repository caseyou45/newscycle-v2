package com.exam.springsecurity.comment.repository;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {


    Iterable<Comment> getCommentsByParticleOrderByDateDesc(Integer particle);

    Iterable<Comment> getCommentsByUsername(String username);

    Comment getCommentByid(Integer id);
}