package com.example.demo.mapper.views;

import com.example.demo.models.Users;
import com.example.demo.response.UseResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserView {
    public UseResponse map(Users user){
        return UseResponse.builder()
                .id(user.getId())
                .name(user.getUsername())
                .email(user.getEmail())
                .build();
    }
    public List<UseResponse> map(List<Users >users){
        List<UseResponse> list = new ArrayList<>();
        for (Users user : users){
            list.add(map(user));
        }
        return list;
    }
}
