package com.security.steff.jpa_jwt.controller;

import com.security.steff.jpa_jwt.models.AuthenticationRequest;
import com.security.steff.jpa_jwt.models.AuthenticationResponse;
import com.security.steff.jpa_jwt.security.JwtUtil;
import com.security.steff.jpa_jwt.services.MyUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@CrossOrigin
@RestController
public class Endpoints {

    @Autowired
    private AuthenticationManager authManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;


    @GetMapping(value="/smth")
    public String smth() {
        return "<h1> Something <h1>";
    }

    @ApiResponses(value= {
        @ApiResponse(code =400, message = "Something went wrong"),
        @ApiResponse(code =403, message = "Access denied"),
        @ApiResponse(code =404, message = "The user doesn't exist"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") //config must EnableGlobalMethodSecurity(prePostEnabled = true)
    @GetMapping(value="/user")
    public String user() {
        return "<h1> User <h1>";
    }

    @ApiResponses(value= {
        @ApiResponse(code =400, message = "Something went wrong"),
        @ApiResponse(code =403, message = "Access denied"),
        @ApiResponse(code =404, message = "The user doesn't exist"),
    })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(value="/admin")
    public String admin() {
        return "<h1> Admin <h1>";
    }


    @ApiResponses(value= {
        @ApiResponse(code =400, message = "Something went wrong"),
        @ApiResponse(code =403, message = "Access denied"),
        @ApiResponse(code =404, message = "The user doesn't exist"),
    })
    @PostMapping(value="/auth")
    public ResponseEntity <?> getAuthToken (@RequestBody AuthenticationRequest authRequest) throws Exception {
        try {
            //use authentication manager with body params
            authManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(),
                 authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception ("Incorrect user or password", e);
        }
        
        final UserDetails userDetails= userDetailsService
                .loadUserByUsername(authRequest.getUsername());
        final String jwt= jwtUtil.generateToken(userDetails);
        
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
    
    
}
