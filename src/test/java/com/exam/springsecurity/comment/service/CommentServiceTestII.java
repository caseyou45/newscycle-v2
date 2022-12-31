package com.exam.springsecurity.comment.service;


import com.exam.springsecurity.comment.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CommentServiceTestII {

    @Autowired
    private CommentService commentService;

    private Comment comment;

    @BeforeEach
    void setup() {


        comment = new Comment();
        comment.setUsername("test");
        comment.setContent("test");
        comment.setAuthor(11);
        comment.setParticle(1);

    }


    @Test
    void addComment() {


        comment = new Comment();
        comment.setUsername("test");
        comment.setContent("test");
        comment.setAuthor(11);
        comment.setParticle(1);

        Comment savedComment = commentService.addComment(comment);
        assertThat(savedComment).isNotNull();

    }
}