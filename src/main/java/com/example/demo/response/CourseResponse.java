package com.example.demo.response;

import com.example.demo.models.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CourseResponse {
    private Long id;
    private String courseName;
    private String application;
    private String fees;
    private Long company_id;

    public CourseResponse(Course course) {
    }
}
