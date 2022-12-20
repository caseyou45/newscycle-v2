package com.exam.springsecurity.vote.repository;

import com.exam.springsecurity.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {

    List<Vote> getVotesByCommentid(Integer commentid);

    List<Vote> getVotesByAuthor(Integer author);
}
