package com.exam.springsecurity.comment.service;


import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTestIII {

    @Mock
    private CommentRepository commentRepository;

    @InjectMocks
    private CommentService commentService;

    private Comment comment;


    @Test
    public void addComment() {


        comment = new Comment();
        comment.setUsername("test");
        comment.setContent("test");
        comment.setAuthor(11);
        comment.setParticle(1);

        //   when(commentRepository.save(comment)).thenReturn(comment);
        // verify(commentRepository).save(comment);

        Comment returnedComment = commentService.addComment(comment);
        assertThat(returnedComment).isNotNull();

        verify(commentRepository).save(comment);
    }
}