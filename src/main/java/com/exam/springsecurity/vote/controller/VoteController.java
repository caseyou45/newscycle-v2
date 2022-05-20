package com.exam.springsecurity.vote.controller;

import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api")


public class VoteController {


    @Autowired
    private VoteService voteService;


    @PostMapping(path = "/vote")
    public @ResponseBody
    Vote makeVote(@RequestBody Vote vote) {
        return voteService.makeVote(vote);

    }

    @PostMapping(path = "/vote/delete")
    public @ResponseBody
    ResponseEntity removeVote(@RequestBody Vote vote) {
        return voteService.removeVote(vote);

    }

    @GetMapping(path = "/vote/{id}")
    public @ResponseBody
    Iterable<Vote> makeVote(@PathVariable Integer id) {
        return voteService.getVotesByCommentID(id);
    }

}



