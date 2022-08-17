package com.exam.springsecurity.user.repository;


import com.exam.springsecurity.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findUserByUsername(String username);


}