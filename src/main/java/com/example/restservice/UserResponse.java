package com.example.restservice;

import com.example.models.User;

import java.util.List;

public record UserResponse(List<User> data) { }

record PostUserResponse(String ok, User data){

}
