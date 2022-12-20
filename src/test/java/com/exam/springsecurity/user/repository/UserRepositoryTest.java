package com.exam.springsecurity.user.repository;

import com.exam.springsecurity.user.model.Users;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    void isUserExistsByUsername() {
        if (!userRepository.findExistByUsername("test")) {
            Users user = new Users("test", "test", new Date(System.currentTimeMillis()));
            userRepository.save(user);
        }
        Users result = userRepository.findUserByUsername("test");
        assertThat(result.getUsername()).isEqualTo("test");


    }

}