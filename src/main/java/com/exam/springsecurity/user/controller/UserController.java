package com.exam.springsecurity.user.controller;

import com.exam.springsecurity.security.models.AuthenticationRequest;
import com.exam.springsecurity.security.models.AuthenticationResponse;
import com.exam.springsecurity.security.services.MyUserDetailsService;
import com.exam.springsecurity.security.util.JwtUtil;
import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import com.exam.springsecurity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@Controller
@RequestMapping(path = "/api")

public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtTokenUtil;
    @Autowired
    private UserRepository userRepository;


    /*  This route process the user signup.

      @param  - takes in user from frontend in request body
      @return  - returns created User

      */
    @PostMapping(path = "/user/auth/signup")
    public @ResponseBody
    ResponseEntity userSignUp(@RequestBody Users user) {

        return userService.userSignUp(user);

    }

    /* User SignIn

     The route passes a user through the auth process. If a user is not found with provided credentials, an
     error is returned. If successful, a jwt is created and returned to the user.

     @param  - takes in user
     @return  - If successful, a jwt is returned

  */
    @RequestMapping(value = "/user/auth/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {


        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Incorrect Username and Password");

        }


        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new AuthenticationResponse(jwt));


    }

    /* This route returns to the signed-in user their details.
        The sign-in process only returns a jwt. This method simply returns the rest of the
        user info that the user will need.


     @param  - takes in username
     @return  - returns user's info

*/

    @GetMapping(path = "/user/details/{username}")
    public @ResponseBody
    Optional<Users> getOneUSer(@PathVariable String username) {
        return userService.getUserByUsername(username);
    }


}
