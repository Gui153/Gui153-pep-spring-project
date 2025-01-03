package com.example.repository;

import com.example.entity.*;
import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface MessageRepository extends JpaRepository<Message, Integer> {
    List<Message> findAllByPostedBy(int postedBy);
}
