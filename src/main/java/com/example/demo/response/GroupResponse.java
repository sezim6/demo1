package com.example.demo.response;

import com.example.demo.models.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GroupResponse {
    private Long id;
    private String groupName;
    private int amountOfStudent;
    private Long company_id;

    public GroupResponse(Group group) {

    }
}
