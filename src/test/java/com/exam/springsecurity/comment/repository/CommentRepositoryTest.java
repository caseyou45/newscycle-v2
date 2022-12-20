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


    @Test
    void getCommentsByParticleOrderByDateDesc() {
        List<Comment> comments = commentRepository.getCommentsByParticleOrderByDateDesc(1);
        assertThat(comments.size()).isGreaterThan(0);
    }

    @Test
    void getCommentsByUsername() {
        List<Comment> comments = commentRepository.getCommentsByUsername("test");
        assertThat(comments.size()).isGreaterThan(0);

    }

    @Test
    void getCommentByid() {
//        Comment comment = commentRepository.getCommentByid(1);
//        assertThat(comment).isNotNull();


    }
}