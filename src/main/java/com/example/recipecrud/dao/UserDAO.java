package com.example.recipecrud.dao;

import java.util.List;

import com.example.recipecrud.entity.User;

public interface UserDAO {

    void save(User user);

    User findByEmail(String email);

    List<User> findAll();    
}
