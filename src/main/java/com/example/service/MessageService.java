package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;



@Service
public class MessageService {

private final MessageRepository messageRepository;
private final AccountRepository accountRepository;;

@Autowired 
public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){

    this.messageRepository = messageRepository;
    this.accountRepository = accountRepository;

}

public Message newMessage(Message message) {
    if (message.getMessageText() == null || message.getMessageText().isEmpty()) {
        throw new IllegalArgumentException("MessageText cannot be empty");
    }
    if (message.getMessageText().length() > 255)
    {
        throw new IllegalArgumentException("MessageText cannot be greater than 255 characters");
    }
    
    Optional<Account> found = accountRepository.findById(message.getPostedBy());

    if (found.isEmpty()) {
        throw new IllegalArgumentException("User does not exist");
    }



    return messageRepository.save(message);
}

public List<Message> getAllMessages(){
    return messageRepository.findAll();
}

public Message getMessageById(int id)
{
   //if the message exists return it otherwise return nothing and throw no exceptions
return messageRepository.findById(id).orElse(null);

}

public int updateMessageText(int id, String newText) {
    if (newText == null || newText.trim().isEmpty()) {
        throw new IllegalArgumentException("Message text cannot be blank");
    }

    if (newText.length() > 255) {
        throw new IllegalArgumentException("Message text too long");
    }

    Optional<Message> optionalMessage = messageRepository.findById(id);
    if (optionalMessage.isEmpty()) {
        throw new IllegalArgumentException("Message not found");
    }

    Message message = optionalMessage.get();
    message.setMessageText(newText);
    messageRepository.save(message);

    return 1;
}

public Integer deleteMessageById(int id) {
    int deletedRows = messageRepository.deleteByMessageId(id);
    return deletedRows == 0 ? null : 1;
}

public List<Message> getMessagesByAccountId(int accountId) {
    return messageRepository.findByPostedBy(accountId);
}


}
