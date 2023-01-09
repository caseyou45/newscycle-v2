package com.exam.springsecurity.vote.service;

import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.repository.VoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
class VoteServiceTest {

    @Autowired
    VoteService voteService;

    @Autowired
    VoteRepository voteRepository;
    private Vote vote;

    @BeforeEach
    void createVote() {
        vote = voteRepository.save(new Vote(1, 29, new java.sql.Date(Calendar.getInstance().getTimeInMillis()), 1, 1));
        assertThat(vote).isNotNull();
    }

    @Test
    void makeVote() {
        Vote savedVote = voteService.makeVote(vote);
        assertThat(savedVote).as("Save New Vote").isNotNull();
    }

    @Test
    void deleteVote() {
        int id = vote.getId();
        voteService.deleteVote(vote);
        assertThat(voteRepository.findById(id)).as("Delete Vote").isEmpty();
    }

    @Test
    void getVotesByCommentID() {
        List<Vote> votesByCommentID = voteService.getVotesByCommentID(29);
        assertThat(votesByCommentID.size()).as("Get Votes By Comment ID").isGreaterThan(0);

    }

    @Test
    void getVotesByUsername() {
        List<Vote> votesByUsername = voteService.getVotesByUsername("testuser");
        assertThat(votesByUsername.size()).as("Get Votes By Username").isGreaterThan(0);


    }

}