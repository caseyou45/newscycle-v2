package com.exam.springsecurity.user.repository;


import com.exam.springsecurity.user.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {
    Users findUserByUsername(String username);


    @Query("select count(u) = 1 from Users u where username = ?1")
    public boolean findExistByUsername(String username);
}