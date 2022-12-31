package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(MockitoExtension.class)
class CommentServiceTest {


    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;


    @BeforeEach
    public void setup() {
        commentRepository = Mockito.mock(CommentRepository.class);
        commentService = new CommentService(commentRepository);

        comment = new Comment();
        comment.setUsername("test");
        comment.setContent("test");
        comment.setAuthor(11);
        comment.setParticle(1);

    }


    @Test
    void addComment() {

        //given(commentRepository.save(comment)).willReturn(comment);
        Comment savedComment = commentService.addComment(comment);
        assertThat(savedComment).isNotNull();

    }
//
//    @Test
//    void getCommentsByParentArticle() {
//    }
//
//    @Test
//    void updateCommentByID() {
//    }
//
//    @Test
//    void deleteCommentByID() {
//    }
//
//    @Test
//    void getCommentsByUsername() {
//    }
//
//    @Test
//    void getCommentByID() {
//    }
}