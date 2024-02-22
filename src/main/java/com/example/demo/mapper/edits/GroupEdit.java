package com.example.demo.mapper.edits;

import com.example.demo.models.Group;
import com.example.demo.response.request.GroupRequest;
import org.springframework.stereotype.Component;

@Component
public class GroupEdit {
    public Group create(GroupRequest request){
        return Group.builder()
                .groupName(request.getGroupName())
                .amountOfStudent(request.getAmountOfStudent()).build();
    }
    public Group update(GroupRequest request, Group group){
        group =Group.builder()
                .groupName(request.getGroupName())
                .amountOfStudent(request.getAmountOfStudent()).build();
    return group;
    }
}
