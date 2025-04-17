package com.example.controller;

import java.util.List;
import java.util.Map;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;

import com.example.service.AccountService;
import com.example.service.MessageService;



/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */


@RestController
public class SocialMediaController {



@Autowired
private AccountService accountService;

@Autowired
private MessageService messageService;

//I should be able to create a new Account on the endpoint POST localhost:8080/register. The body will contain a representation of a JSON Account, but will not contain an accountId.



@PostMapping(value = "/register")
public Account createNewUser(@RequestBody Account account)
{
    
    return accountService.newAccount(account);
}

@PostMapping(value = "/login")
public Account loginUser(@RequestBody Account account)
{
    
    return accountService.loginToAccount(account);
}


@PostMapping(value = "/messages")
public Message createNewMessage(@RequestBody Message message)
{
    
    return messageService.newMessage(message);
}

@GetMapping(value = "/messages")
public List<Message> getAllMessages()
{

    return messageService.getAllMessages();
}

@GetMapping(value = "/messages/{messageId}")
public Message getMessageById(@PathVariable int messageId)
{
    return messageService.getMessageById(messageId);
}

@PatchMapping(value = "/messages/{messageId}")
public int updateMessageById(@PathVariable int messageId, @RequestBody Map<String, String> body)
{
    String newText = body.get("messageText");
    return messageService.updateMessageText(messageId, newText);
}

@DeleteMapping("/messages/{messageId}")
public ResponseEntity<?> deleteMessage(@PathVariable int messageId) {
    Integer result = messageService.deleteMessageById(messageId);
    if (result == null) {
        return ResponseEntity.ok().build(); // empty body
    }
    return ResponseEntity.ok(result); // body = 1
}

@GetMapping("/accounts/{accountId}/messages")
public List<Message> getMessagesByAccountId(@PathVariable int accountId) {
    return messageService.getMessagesByAccountId(accountId);
}



}
