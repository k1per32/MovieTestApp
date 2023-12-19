package com.example.demo.dao;

import com.example.demo.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsersRepository extends JpaRepository<Users, Long> {

    Optional<Users> findAllByEmail(String email);
    Optional<Users> findAllByUsername(String username);
}
