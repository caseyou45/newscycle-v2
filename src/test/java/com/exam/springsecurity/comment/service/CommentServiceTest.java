package com.exam.springsecurity.comment.service;

import com.exam.springsecurity.comment.model.Comment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class CommentServiceTest {


    @Autowired
    CommentService commentService;

    private Comment comment;


    @BeforeEach
    public void setup() {

        comment = new Comment();
        comment.setUsername("testuser");
        comment.setContent("test comment");
        comment.setAuthor(1);
        comment.setParticle(1);
        comment.setDeleted(false);

    }

    //Tests that a comment can be added

    @Test
    void addComment() {
        Comment savedComment = commentService.addComment(comment);
        assertThat(comment.getUsername()).as("Save New Comment").isEqualTo(savedComment.getUsername());

    }

    //Tests that a List of comments can be retrieved by article ID

    @Test
    void getCommentsByParentArticle() {
        List<Comment> commentList = commentService.getCommentsByParentArticle(1);
        assertThat(commentList.size()).as("Get Comments By Parent Article").isGreaterThan(0);

    }

    //Test that a comment can be updated
    @Test
    void updateCommentByID() {
        Comment savedComment = commentService.addComment(comment);
        String newContent = "NEW CONTENT";
        comment.setContent(newContent);
        Comment updatedComment = commentService.updateCommentByID(comment);
        assertThat(newContent).as("Check Updated Comment Saved").isEqualTo(updatedComment.getContent());


    }

    //Tests that a comment can be "deleted" by ID. Because of comment threading on the Frontend, a comment can not be actually deleted.
    //Instead, the username, content, and author ID are set to dummy values
    @Test
    void deleteCommentByID() {
        Comment savedComment = commentService.addComment(comment);
        Comment deletedComment = commentService.deleteCommentByID(savedComment.getId());
        assertThat(deletedComment.getAuthor()).as("Deleted Comment").isEqualTo(5);
    }

    //Test that a List of comments can be retrieved by username
    @Test
    void getCommentsByUsername() {

        List<Comment> comments = commentService.getCommentsByUsername("testuser");
        assertThat(comments.size()).as("Get Comments By Username").isGreaterThan(0);


    }

    //Tests that a comment be retrieved by ID
    @Test
    void getCommentByID() {
        Comment comment = commentService.getCommentByID(1);
        assertThat(comment).as("Get Comment by ID").isNotNull();
    }
}