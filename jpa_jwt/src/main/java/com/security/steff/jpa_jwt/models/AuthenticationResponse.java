package com.security.steff.jpa_jwt.models;

import java.io.Serializable;

public class AuthenticationResponse implements Serializable{

    public final String jtw;

    public AuthenticationResponse(String jtw) {
        this.jtw = jtw;
    }

    public String getJtw() {
        return jtw;
    }    
    
}
