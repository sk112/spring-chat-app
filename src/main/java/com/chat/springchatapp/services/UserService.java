package com.chat.springchatapp.services;

import java.util.HashMap;

import com.chat.springchatapp.config.AppConfig;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    public static HashMap<String, AppConfig> appConfigs = new HashMap<>();

    UserService(){}

}