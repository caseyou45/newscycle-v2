package com.exam.springsecurity.security.models;

public class AuthenticationResponse {

    private final String jwt;
    private final Integer id;
    private final String username;

    public AuthenticationResponse(String jwt, Integer id, String username) {
        this.jwt = jwt;
        this.id = id;
        this.username = username;
    }

    public String getJwt() {
        return jwt;
    }

    public Integer getID() {
        return id;
    }

    public String getUsername() {
        return username;
    }
}
