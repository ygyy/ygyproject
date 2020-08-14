package com.ygy.annotation.service;

import com.ygy.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void add(){

        System.out.println("service add....");
        userRepository.add();
    }
}
