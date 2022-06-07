package com.exam.springsecurity.vote.repository;

import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.vote.model.Vote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface VoteRepository extends JpaRepository<Vote, Integer> {


    Iterable<Vote> getVotesByCommentid(Integer commentid);

    Iterable<Vote> getVotesByAuthor(Integer author);
}
