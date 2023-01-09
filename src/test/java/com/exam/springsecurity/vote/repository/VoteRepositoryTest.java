package com.exam.springsecurity.vote.repository;

import com.exam.springsecurity.vote.model.Vote;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class VoteRepositoryTest {

    @Autowired
    VoteRepository voteRepository;

    @Test
    void getVotesByCommentid() {
        List<Vote> votes = voteRepository.getVotesByCommentid(29);
        assertThat(votes.size()).as("Get Votes By Comment ID").isGreaterThan(0);


    }

    @Test
    void getVotesByAuthor() {
        List<Vote> votes = voteRepository.getVotesByAuthor(1);
        assertThat(votes.size()).as("Get Votes By Voter ID").isGreaterThan(0);
    }
}