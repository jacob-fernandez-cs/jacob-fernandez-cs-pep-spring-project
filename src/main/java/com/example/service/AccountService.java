package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.exception.CustomException.BadLoginException;
import com.example.exception.CustomException.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;


@Service
public class AccountService {

private final AccountRepository accountRepository;

@Autowired 
public AccountService(AccountRepository accountRepository){

    this.accountRepository = accountRepository;

}

public Account newAccount(Account account) {
    if (account.getUsername() == null || account.getUsername().isEmpty()) {
        throw new IllegalArgumentException("Username cannot be empty");
    }

    if (accountRepository.findByUsername(account.getUsername()).isPresent()) {
        throw new UsernameAlreadyExistsException("Username already exists");
    }

    return accountRepository.save(account);
}

public Account loginToAccount(Account account){
   
   
    if (account.getUsername() == null || account.getUsername().isEmpty()) {
        throw new BadLoginException("Username cannot be empty");
    }
    if (account.getPassword() == null || account.getPassword().isEmpty()) {
        throw new BadLoginException("Password cannot be empty");

    }
    
    Optional<Account> found = accountRepository.findByUsername(account.getUsername());

    if (found.isEmpty()) {
        throw new BadLoginException("Username does not exist");
    }

    Account existingAccount = found.get();

    if (!existingAccount.getPassword().equals(account.getPassword())) {
        throw new BadLoginException("Invalid password");
    }

    return existingAccount;

    
}

}


