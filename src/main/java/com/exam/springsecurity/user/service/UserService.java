package com.exam.springsecurity.user.service;

import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /*    This method returned to a signed-in user their own details.

      @param  username of requested user
      @return  user's details

      */

    public Optional<Users> getUserByUsername(String username) {
        Optional<Users> user = Optional.ofNullable(userRepository.findUserByUsername(username));

        return user;
    }


    /*    This method performs the user sign-up process. It takes in a user and checks to see if
          the username is in use. If not, it appends to the user the creation date for the user's account.
          It then encrypts the user's password and saves the user.

         @param  username of requested user
         @return  user's details

  */


    public ResponseEntity userSignUp(Users user) {
        Optional<Users> userOptionalByUsername = Optional.ofNullable(userRepository.findUserByUsername(user.getUsername()));

        if (userOptionalByUsername.isPresent()) {
            return new ResponseEntity("Username already in use",
                    HttpStatus.FORBIDDEN);

        }


        //Setting the user's account creation date
        Date utilDate = new java.util.Date();
        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
        user.setCreationdate(sqlDate);

        //Encryption of password
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);


        userRepository.save(user);


        return new ResponseEntity(HttpStatus.OK);
    }


    /*    This UNUSED method performs a sign-in process. It does not use JWT and merely checks credentials.

     */
    public ResponseEntity<String> userSignIn(Users unverifiedUser) {
        Users dbUser = userRepository.findUserByUsername(unverifiedUser.getUsername());

        if (dbUser == null) {
            return new ResponseEntity<String>(
                    "User not found",
                    HttpStatus.BAD_REQUEST);

        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        if (passwordEncoder.matches(unverifiedUser.getPassword(), dbUser.getPassword())) {
            return new ResponseEntity<String>(dbUser.getUsername(),
                    HttpStatus.OK);

        } else {
            return new ResponseEntity<String>("Incorrect Password",
                    HttpStatus.FORBIDDEN);
        }

    }


}
