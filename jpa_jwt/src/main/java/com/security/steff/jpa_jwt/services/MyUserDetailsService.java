package com.security.steff.jpa_jwt.services;

import java.util.Optional;

import com.security.steff.jpa_jwt.models.MyUserDetails;
import com.security.steff.jpa_jwt.models.User;
import com.security.steff.jpa_jwt.repositories.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        Optional <User> user= userRepository.findByUserName(username); //call db and look for that username

        user.orElseThrow(()-> new UsernameNotFoundException("User not found" +username)); 

        return user.map(MyUserDetails::new).get();  //change user to UserDetails object
    }
    
}
