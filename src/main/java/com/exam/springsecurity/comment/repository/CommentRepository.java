package com.exam.springsecurity.comment.repository;

import com.exam.springsecurity.comment.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query(
            value = "SELECT * FROM COMMENT c WHERE c.parent_article = ?1",
            nativeQuery = true)
    Iterable<Comment> getCommentsByParentArticle(Integer parent_article);

}