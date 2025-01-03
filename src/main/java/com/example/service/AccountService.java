package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.ComponentScan;

import com.example.entity.*;
import com.example.repository.AccountRepository;

@ComponentScan(basePackages = "com.example.repository")
@Service
//check if data is correct
public class AccountService {
    @Autowired
    public AccountRepository accRepository;


    public ResponseEntity registerUser(Account user){
        //System.out.println("in service");
        //check if username is unique
        
        Optional<Account> checkUser = accRepository.findByUsername(user.getUsername());
        if(!checkUser.isEmpty()){
            return ResponseEntity.status(409).body("conflict");
        }
        
        Account acc = accRepository.save(user);
        //System.out.println("new acc: "+acc);
        return ResponseEntity.status(200).body(acc);
    }

    public ResponseEntity loginUser(Account user){

        //System.out.println("in service");
        //check if user exists
        Optional<Account> checkUser = accRepository.findByUsername(user.getUsername());
        if(checkUser.isEmpty()){
            return ResponseEntity.status(401).body("Unauthorized");
        }
        //check if password match
        Account acc = checkUser.get();
        //System.out.println(acc);
        if(acc.getPassword().equals( user.getPassword())){
            return ResponseEntity.status(200).body(acc);
        }

        return ResponseEntity.status(401).body("Unauthorized");
    }

}
