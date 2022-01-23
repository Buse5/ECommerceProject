package com.works.services;

import com.works.entities.Users;
import com.works.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

public interface  UserService{
    Users findOne(String email);

    Collection<Users> findByRole(String role);

    Users save(Users user);

    Users update(Users user);
}
