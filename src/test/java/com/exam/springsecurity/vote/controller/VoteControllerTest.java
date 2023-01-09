package com.exam.springsecurity.vote.controller;


import com.exam.springsecurity.security.services.MyUserDetailsService;
import com.exam.springsecurity.security.util.JwtUtil;
import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.repository.VoteRepository;
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

import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class VoteControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private VoteRepository voteRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private MyUserDetailsService myUserDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    private String jwt;

    private Vote vote;


    @BeforeEach
    void createTokenIfNull() {
        if (jwt == null) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken("testuser", "testpassword"));

            final UserDetails userDetails = myUserDetailsService.loadUserByUsername("testuser");

            jwt = "Bearer " + jwtUtil.generateToken(userDetails);
        }

    }

    @BeforeEach
    void createVoteIfNull() {
        if (vote == null) {
            vote = new Vote(1, 29, new java.sql.Date(Calendar.getInstance().getTimeInMillis()), 1, 1);
            voteRepository.save(vote);
        }
    }

    @Test
    void makeVote() {

        assertDoesNotThrow(() -> mockMvc.perform(post("/api/vote")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", jwt)
                        .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isOk()));

    }


    @Test
    void deleteVote() {

        assertDoesNotThrow(() -> mockMvc.perform(patch("/api/vote/delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("authorization", jwt)
                        .content(objectMapper.writeValueAsString(vote)))
                .andExpect(status().isOk()));


    }

    @Test
    void getVotesByCommentID() {

        assertDoesNotThrow(() -> mockMvc.perform(get("/api/vote?id=" + vote.getId()))
                .andExpect(status().isOk()));

    }

    @Test
    void getVotesByUsername() {
        assertDoesNotThrow(() -> mockMvc.perform(get("/api/vote/user?username=testuser"))
                .andExpect(status().isOk()));

    }


}