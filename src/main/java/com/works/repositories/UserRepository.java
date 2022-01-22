package com.works.repositories;

import com.works.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
    Collection<Users> findAllByRole(String role);
}
