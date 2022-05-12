package com.exam.springsecurity.user.service;

import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;


@Component
public class UserService {

    @Autowired
    private UserRepository userRepository;


    public String addUser(Users user) {
        Optional<Users> userOptionalByUsername = Optional.ofNullable(userRepository.findUserByUsername(user.getUsername()));

        if (userOptionalByUsername.isPresent()) {
            throw new IllegalStateException("Username In Use");
        }
        userRepository.save(user);
        return "User Saved";

    }


    public Iterable<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<Users> getOneUserByID(Integer id) {
        return userRepository.findById(id);
    }

    public String deleteUser(Integer id) {
        boolean exists = userRepository.existsById(id);
        if (!exists) {
            throw new IllegalStateException("No user ( " + id + " ) exists.");
        } else {
            userRepository.deleteById(id);
        }

        return "User removed";
    }

    public Optional<Users> getUserByUsername(String username) {
        Optional<Users> user = Optional.ofNullable(userRepository.findUserByUsername(username));

        if (!user.isPresent()) {
            throw new IllegalStateException("No user ( " + username + " ) exists.");
        }

        return user;
    }

    @Transactional
    public String updateUser(Integer id, String username, String password) {
        Users user = userRepository.findById(id)
                .orElseThrow(() -> {
                    throw new IllegalStateException("No user ( " + id + " ) exists.");
                });


        if (username != null && username.length() > 0 && !Objects.equals(user.getUsername(), username)) {
            user.setName(username);

            return "Name Updated";
        }


        if (password != null && password.length() > 0 && !Objects.equals(user.getPassword(), password)) {
            user.setPassword(password);
            return "Password Updated";
        }


        return "Update not successful";

    }


    public Optional<Users> userSignUp(Users user) {
        Optional<Users> userOptionalByUsername = Optional.ofNullable(userRepository.findUserByUsername(user.getUsername()));

        if (userOptionalByUsername.isPresent()) {
            throw new IllegalStateException("Username In Use");

        }

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setPassword(encodedPassword);

        Users savedUser = userRepository.save(user);


        return Optional.of(savedUser);
    }


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
