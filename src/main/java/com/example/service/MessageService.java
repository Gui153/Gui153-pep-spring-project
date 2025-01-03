package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.*;
@Service
public class MessageService {

    @Autowired
    public MessageRepository messageRepository;
    @Autowired
    public AccountRepository accountRepository;

    public ResponseEntity createMsg(Message msg){
        Optional<Account> check = accountRepository.findById(msg.getPostedBy());

        if(check.isEmpty()){
            return ResponseEntity.status(400).body("Client error");
        }

        

        return ResponseEntity.status(200).body(messageRepository.save(msg));
    }
}
