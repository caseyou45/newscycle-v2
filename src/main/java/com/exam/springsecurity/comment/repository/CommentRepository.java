package com.exam.springsecurity.comment.repository;

import com.exam.springsecurity.article.model.Article;
import com.exam.springsecurity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

//    @Query(
//            value = "SELECT * FROM COMMENT c WHERE c.parent_article = ?1",
//            nativeQuery = true)
//    Iterable<Comment> getCommentsByParentArticle(Integer parent_article);

    Iterable<Comment> getCommentsByParticleOrderByDateDesc(Integer particle);

}