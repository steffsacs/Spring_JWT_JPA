package com.security.steff.jpa_jwt.models;

import java.io.Serializable;

public class AuthenticationRequest implements Serializable{

    private String username;
    private String password;

    //Default constructor for JSON
    public AuthenticationRequest() {
    }

    public AuthenticationRequest(String userName, String password) {
        this.username = userName;
        this.password = password;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String userName) {
        this.username = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    
}
