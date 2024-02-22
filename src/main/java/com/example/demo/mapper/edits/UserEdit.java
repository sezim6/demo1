package com.example.demo.mapper.edits;

import com.example.demo.models.Users;
import com.example.demo.response.request.UseRequest;

public class UserEdit {
    public Users create(UseRequest request){
        return Users.builder()
                .username(request.getName())
                .email(request.getEmail())
                .password(request.getPassword()).build();
    }
    public Users update(UseRequest request , Users user ){
        user = Users.builder()
                .username(request.getName())
                .email(request.getEmail())
                .password(request.getPassword()).build();
        return user;
    }
}

