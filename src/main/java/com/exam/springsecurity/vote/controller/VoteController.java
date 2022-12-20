package com.exam.springsecurity.vote.controller;

import com.exam.springsecurity.vote.model.Vote;
import com.exam.springsecurity.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(path = "/api")


public class VoteController {


    private VoteService voteService;


    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

        /*  Route for making one vote.
         @param  the vote in the request body
         @return the vote once saved


     */


    @PostMapping(path = "/vote")
    public @ResponseBody
    Vote makeVote(@RequestBody Vote vote) {
        return voteService.makeVote(vote);

    }

 /*    Route for 'deleting' one vote. Note that the method in not delete. Springboot
       was having difficulties with the delete method. So post is used.
         @param  the vote in the request body
         @return the vote once deleted


     */

    @PostMapping(path = "/vote/delete")
    public @ResponseBody
    ResponseEntity removeVote(@RequestBody Vote vote) {
        return voteService.removeVote(vote);

    }

    /*    Route for getting all votes associated with a comment
         @param  the id of the comment
         @return the votes for that comment

     */

    @GetMapping(path = "/vote/{id}")
    public @ResponseBody
    List<Vote> getVotesByCommentID(@PathVariable Integer id) {
        return voteService.getVotesByCommentID(id);
    }

    /*    Route for getting all votes associated with a user

      @param  the user's username as a path variable
      @return the votes make by the user

  */
    @GetMapping(path = "/vote/user/{username}")
    public @ResponseBody
    List<Vote> getCommentsMadeByUser(@PathVariable String username) {
        return voteService.getVotesByUsername(username);
    }
}



