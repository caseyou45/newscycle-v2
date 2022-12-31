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



    /*Creates a user with username/password of test if none exists. Then checks that user test
     * can be found by username. Includes option to delete user.*/

    @Test
    void isUserExistsByUsername() {
        if (!userRepository.findExistByUsername("test")) {
            Users user = new Users("test", "test", new Date(System.currentTimeMillis()));
            userRepository.save(user);
        }
        Users result = userRepository.findUserByUsername("test");
        assertThat(result.getUsername()).isEqualTo("test");

//        //Option to delete (and ensure deletion of) user to ensure test user is not persisted.
//        userRepository.delete(result);
//        assertThat(userRepository.findUserByUsername("test")).isNull();


    }

}