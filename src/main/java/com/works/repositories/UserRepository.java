package com.works.repositories;

import com.works.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {
    Users findByEmail(String email);
    Collection<Users> findAllByRole(String role);
}
