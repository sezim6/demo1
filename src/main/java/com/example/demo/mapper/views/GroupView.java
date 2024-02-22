package com.example.demo.mapper.views;

import com.example.demo.models.Group;
import com.example.demo.response.GroupResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class GroupView {
    public GroupResponse map(Group group){
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .amountOfStudent(group.getAmountOfStudent()).build();
    }
    public List<GroupResponse> map(List<Group >groups){
        List<GroupResponse> list = new ArrayList<>();
        for ( Group group : groups){
            list.add(map(group));
        }
        return list;
    }
}
