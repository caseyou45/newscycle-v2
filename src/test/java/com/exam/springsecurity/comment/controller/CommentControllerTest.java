package com.exam.springsecurity.comment.controller;

import com.exam.springsecurity.comment.repository.CommentRepository;
import com.exam.springsecurity.security.services.MyUserDetailsService;
import com.exam.springsecurity.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import com.exam.springsecurity.comment.model.Comment;

import java.util.Calendar;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class CommentControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private Comment comment;

    private String jwt;


    String randomStringGenerator(int length) {

        int leftLimit = 97;
        int rightLimit = 122;

        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            int randomLimitedInt = leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
            stringBuilder.append((char) randomLimitedInt);
        }

        return stringBuilder.toString();

    }


    @BeforeEach
    void createTokenIfNull() {
        if (jwt == null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("testuser", "testpassword"));

            final UserDetails userDetails = myUserDetailsService.loadUserByUsername("testuser");

            jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        }

    }

    @BeforeEach
    void createCommentIfNull() {
        if (comment == null) {
            String commentContent = randomStringGenerator(10);
            comment = new Comment(1, commentContent, new java.sql.Date(Calendar.getInstance().getTimeInMillis()), 1, null, "testuser", false);
            commentRepository.save(comment);
        }
    }

    @Test
    void addComment() {


        assertDoesNotThrow(() -> mockMvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", jwt)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk()));


    }

    @Test
    void getCommentsByParentArticle() {

        assertDoesNotThrow(() -> mockMvc.perform(get("/api/comment/article?id=1"))
                .andExpect(status().isOk()));


    }

    @Test
    void updateCommentByID() {


        String newContent = randomStringGenerator(10);
        comment.setContent(newContent);


        assertDoesNotThrow(() -> mockMvc.perform(patch("/api/comment/edit")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", jwt)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk()));


    }

    @Test
    void getCommentByID() {

        assertDoesNotThrow(() -> mockMvc.perform(get("/api/comment?id=" + comment.getId()))
                .andExpect(status().isOk()));
    }

    @Test
    void deleteCommentByID() {

        assertDoesNotThrow(() -> mockMvc.perform(patch("/api/comment/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", jwt)
                        .content(objectMapper.writeValueAsString(comment)))
                .andExpect(status().isOk()));

    }

    @Test
    void getCommentsMadeByUser() {

        assertDoesNotThrow(() -> mockMvc.perform(get("/api/comment/user?username=testuser"))
                .andExpect(status().isOk()));

    }


}