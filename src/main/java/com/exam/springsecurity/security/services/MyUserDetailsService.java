package com.exam.springsecurity.security.services;

import com.exam.springsecurity.security.CustomUserDetails;
import com.exam.springsecurity.user.model.Users;
import com.exam.springsecurity.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {


    private UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Users user = userRepository.findUserByUsername(username);

        if (null == user || !user.getUsername().equals(username)) {
            throw new UsernameNotFoundException("No user present with username: " + username);

        } else {

            return new CustomUserDetails(user);
        }


    }

}
