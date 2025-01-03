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

    public ResponseEntity getMsg(){

        return ResponseEntity.status(200).body(messageRepository.findAll());
    }

    public ResponseEntity getMsgById(int id){

        Optional<Message> msg = messageRepository.findById(id);
        if(msg.isEmpty()){
            return ResponseEntity.status(200).body("");
        }
        return ResponseEntity.status(200).body(msg.get());
    }

    public ResponseEntity deleteMsgById(int id){

        Optional<Message> msg = messageRepository.findById(id);
        if(msg.isEmpty()){
            return ResponseEntity.status(200).body("");
        }
        messageRepository.deleteById(id);
        
        
        return ResponseEntity.status(200).body("1");
    }

    public ResponseEntity patchMsgById(int id, Message updateMsg){

        Optional<Message> check = messageRepository.findById(id);
        
        if(check.isEmpty()){
            return ResponseEntity.status(400).body("Client error");
        }

        Message originalMsg = check.get();
        originalMsg.setMessageText(updateMsg.getMessageText());
        messageRepository.save(originalMsg);
        

        return ResponseEntity.status(200).body("1");
    }

    public ResponseEntity getMsgByUserId(int userId){



        return ResponseEntity.status(200).body(messageRepository.findAllByPostedBy(userId));

    }
}
