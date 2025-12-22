package com.example.demo.service;

import com.example.demo.model.UserAccount;

public interface AuthService {

    UserAccount register(UserAccount user);

    UserAccount login(String username, String password);
}
