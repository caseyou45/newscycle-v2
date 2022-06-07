package com.exam.springsecurity.vote.service;


import com.exam.springsecurity.comment.model.Comment;
import com.exam.springsecurity.comment.repository.CommentRepository;
import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.repository.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class VoteService {

    @Autowired
    private VoteRepository voteRepository;

    @Autowired
    private UserRepository userRepository;

    public Vote makeVote(Vote vote) {
        voteRepository.save(vote);
        return vote;

    }

    public ResponseEntity removeVote(Vote vote) {
        voteRepository.deleteById(vote.getId());
        return new ResponseEntity(HttpStatus.OK);
    }

    public Iterable<Vote> getVotesByCommentID(Integer id) {
        return voteRepository.getVotesByCommentid(id);
    }

    public Iterable<Vote> getVotesByUsername(String username) {
        Users user = userRepository.findUserByUsername(username);
        return voteRepository.getVotesByAuthor(user.getId());
    }
}
