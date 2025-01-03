package com.example.controller;

import com.example.entity.*;
import com.example.service.*;

import org.jboss.logging.Messages;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@ComponentScan(basePackages = "com.example.service")
@RestController
public class SocialMediaController {
    
    public AccountService accService;
    public MessageService msgService;
    public SocialMediaController(AccountService acc, MessageService msg){
        this.accService = acc;
        this.msgService = msg;
    }

    @PostMapping(value="/register")
    public ResponseEntity postRegister(@RequestBody Account newUser){
        //you will need to change the method's parameters and return the extracted path variable.
        if(newUser.getUsername().length() == 0 || newUser.getPassword().length() < 4){
            return ResponseEntity.status(400).body("Client error");
        }
        return accService.registerUser(newUser);
    }

    @PostMapping(value="/login")
    public ResponseEntity postLogin(@RequestBody Account user){
        //you will need to change the method's parameters and return the extracted path variable.
        if(user.getUsername().length() == 0 || user.getPassword().length() < 4){
            return ResponseEntity.status(401).body("Unauthorized");
        }
        return accService.loginUser(user);
    }

    @PostMapping(value="/messages")
    public ResponseEntity postMessage(@RequestBody Message msg){
        
        //you will need to change the method's parameters and return the extracted path variable.
        //System.out.println("in msg controler");
        //System.out.println(msg);
        if(msg.getMessageText().length() >255 || msg.getMessageText().isEmpty()){
            return ResponseEntity.status(400).body("Client error");
        }
        return msgService.createMsg(msg);
    }

    @GetMapping(value="/messages")
    public ResponseEntity getMessage(){
        
        //you will need to change the method's parameters and return the extracted path variable.
        // System.out.println("in msg controler");
        
        
        return msgService.getMsg();
    }

    @GetMapping(value="/messages/{messageId}")
    public ResponseEntity getMessageById(@PathVariable int messageId){
        
        //you will need to change the method's parameters and return the extracted path variable.
        //System.out.println("in msg controler");
        
        
        return msgService.getMsgById(messageId);
    }
    @DeleteMapping(value="/messages/{messageId}")
    public ResponseEntity deleteMessageById(@PathVariable int messageId){
        
        //you will need to change the method's parameters and return the extracted path variable.
        //System.out.println("in msg controler");
        
        
        return msgService.deleteMsgById(messageId);
    }

    @PatchMapping(value="/messages/{messageId}")
    public ResponseEntity patchMessageById(@PathVariable int messageId, @RequestBody Message msg){
        
        //you will need to change the method's parameters and return the extracted path variable.
        //System.out.println("in msg controler");
        if(msg.getMessageText().length() >255 || msg.getMessageText().isEmpty()){
            return ResponseEntity.status(400).body("Client error");
        }
        
        return msgService.patchMsgById(messageId, msg);
    }

    @GetMapping(value="/accounts/{accountId}/messages")
    public ResponseEntity getMessageByUserId(@PathVariable int accountId){
        
        //you will need to change the method's parameters and return the extracted path variable.
        System.out.println("in msg controler");
        
        System.out.println(accountId);
        return msgService.getMsgByUserId(accountId);
    }
}
