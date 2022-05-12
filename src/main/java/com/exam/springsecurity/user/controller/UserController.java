package com.exam.springsecurity.user.controller;

import com.exam.springsecurity.security.models.AuthenticationRequest;
import com.exam.springsecurity.security.models.AuthenticationResponse;
import com.exam.springsecurity.security.services.MyUserDetailsService;
import com.exam.springsecurity.security.util.JwtUtil;
import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


    /* Basic user creation
     *
     * @Param  - takes in user from frontend in request body
     * @Return  - returns String saying if user creation was successful or not*/
    @PostMapping(path = "/user")
    public @ResponseBody
    String addUser(@RequestBody Users user) {
        return userService.addUser(user);

    }

    /*Returns all users

     * @Return - returns all users in JSON */
    @GetMapping(path = "/user")
    public @ResponseBody
    Iterable<Users> getAllUsers() {
        return userService.getAllUsers();
    }


    /* Returns one user by id
     *
     * @Param  - takes one user id as variable from URI
     * @Return  - returns String saying if user creation was successful or not*/
    @GetMapping(path = "/user/{id}")
    public @ResponseBody
    Optional<Users> getOneUSer(@PathVariable Integer id) {
        return userService.getOneUserByID(id);
    }


    /* Basic user update
     *
     * @Param  - takes in user from request body and id as variable from URI
     * @Return  - returns String saying if user update was successful or not*/
    @PutMapping(path = "/user/{id}")
    public @ResponseBody
    String updateUser(@RequestBody Users updatedUser, @PathVariable Integer id) {
        return userService.updateUser(id, updatedUser.getUsername(), updatedUser.getPassword());
    }


    /* Basic user deletion
     *
     * @Param  - takes in user from frontend in request body
     * @Return  - returns String saying if user deletion was successful or not*/
    @DeleteMapping(path = "/user/{id}")
    public @ResponseBody
    String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }


    /* User SignUp
     *
     * @Param  - takes in user from frontend in request body
     * @Return  - returns created User*/
    @PostMapping(path = "/user/auth/signup")
    public @ResponseBody
    Optional<Users> userSignUp(@RequestBody Users user) {

        return userService.userSignUp(user);

    }

//    @PostMapping(path = "/user/auth/signin")
//    public @ResponseBody
//    ResponseEntity<String> userSignin(@RequestBody Users user) {
//        return userService.userSignIn(user);
//
//    }

    @RequestMapping(value = "/user/auth/signin", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword()));

        } catch (BadCredentialsException e) {
            throw new Exception("Incorrect Username and Password");
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);


        return ResponseEntity.ok(new AuthenticationResponse(jwt));


    }


}
