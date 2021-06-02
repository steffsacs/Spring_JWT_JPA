package com.security.steff.jpa_jwt.repositories;

import java.util.Optional;

import com.security.steff.jpa_jwt.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Integer>  {

    Optional <User>  findByUserName(String userName);

    
}
