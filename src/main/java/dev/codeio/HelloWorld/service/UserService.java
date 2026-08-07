package dev.codeio.HelloWorld.service;


import dev.codeio.HelloWorld.models.User;
import dev.codeio.HelloWorld.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;



@Service
public class UserService {
    //Autowire
    @Autowired
    private UserRepository UserRepository;
    public User  createUser(User user)
    {
        return UserRepository.save(user);
    }
    public User getUserById(Long id)
    {
        return UserRepository.findById(id).orElseThrow(()->new RuntimeException("User Not Found"));
    }




}
