package com.exam.springsecurity.user.model;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "user")
public class Users {
    @Id
    @SequenceGenerator(name = "user_sequence", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")

    private Integer id;
    private String username;
    private String password;
    private Date creationdate;


    public Users() {
    }

    public Users(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public Users(String name, String password, Date creationdate) {
        this.username = name;
        this.password = password;
        this.creationdate = creationdate;
    }

    public Users(Integer id, String username, String password, Date creationdate) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.creationdate = creationdate;

    }

    public Date getCreationdate() {
        return creationdate;
    }

    public void setCreationdate(Date creationdate) {
        this.creationdate = creationdate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}