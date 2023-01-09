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


    private final VoteService voteService;


    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

        /*  Route for making one vote.
         @param  the vote in the request body
         @return the vote once saved
         @Example request URI /api/vote

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
         @Example request URI /api/vote/delete

     */

    @PatchMapping(path = "/vote/delete")
    public @ResponseBody
    ResponseEntity deleteVote(@RequestBody Vote vote) {
        return voteService.deleteVote(vote);

    }

    /*    Route for getting all votes associated with a comment
         @param  the id of the comment
         @return the votes for that comment
         @Example request URI /api/vote?id=1

     */

    @GetMapping(path = "/vote")
    public @ResponseBody
    List<Vote> getVotesByCommentID(@RequestParam Integer id) {
        return voteService.getVotesByCommentID(id);
    }

    /*    Route for getting all votes associated with a user

      @param  the user's username as a path variable
      @return the votes make by the user
      @Example request URI /api/vote/user?username=fooname

  */
    @GetMapping(path = "/vote/user")
    public @ResponseBody
    List<Vote> getCommentsMadeByUser(@RequestParam String username) {
        return voteService.getVotesByUsername(username);
    }
}



