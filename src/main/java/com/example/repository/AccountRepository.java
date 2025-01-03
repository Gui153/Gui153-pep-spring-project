package com.example.repository;

import com.example.entity.*;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


//extends Jpa for ease of access
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Optional<Account> findByUsername(String username);
}
