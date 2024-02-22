package com.example.demo.response;

import com.example.demo.models.Students;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String email;
    private String majorField;
    private int personalId;
    private Long group_id;

    public StudentResponse(Students students) {

    }
}
