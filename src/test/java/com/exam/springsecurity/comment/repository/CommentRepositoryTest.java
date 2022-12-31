package com.exam.springsecurity.comment.repository;

import com.exam.springsecurity.comment.model.Comment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class CommentRepositoryTest {

    @Autowired
    CommentRepository commentRepository;


    //Tests that comments can be retrieved by associated article.
    @Test
    void getCommentsByParticleOrderByDateDesc() {
        List<Comment> comments = commentRepository.getCommentsByParticleOrderByDateDesc(1);
        assertThat(comments.size()).as("Get Comment By Article and Order by Date").isGreaterThan(0);
    }

    //Tests that comments by username can be found. "testuser" user is used.
    @Test
    void getCommentsByUsername() {
        List<Comment> comments = commentRepository.getCommentsByUsername("testuser");
        assertThat(comments.size()).as("Get Comment By Username").isGreaterThan(0);

    }

    @Test
    void getCommentByid() {
        Comment comment = commentRepository.getById(1);
        assertThat(comment).as("Get Comment By ID").isNotNull();


    }
}