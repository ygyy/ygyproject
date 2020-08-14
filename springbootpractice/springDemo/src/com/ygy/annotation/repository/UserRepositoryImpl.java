package com.ygy.annotation.repository;

import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository{

    @Override
    public void add() {
        System.out.println(" dao add...");
    }
}
