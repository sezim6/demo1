package com.example.demo.response.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherRequest {
    private String name;
    private String email;
    private int personalId;
    private String majorField;
}
