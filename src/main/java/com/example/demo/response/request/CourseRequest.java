package com.example.demo.response.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseRequest {
    private String courseName;
    private String application;
    private String fees;
//    private Long company_id;
}
