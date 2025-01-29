package com.example;

public class UserService {
    public String getUser(int id) {
        if (id == 1) {
            return "{\"id\": 1, \"name\": \"John Doe\"}";
        } else {
            throw new IllegalArgumentException("User not found");
        }
    }
}