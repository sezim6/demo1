package com.example.demo.response;

import com.example.demo.models.Teacher;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TeacherResponse {
    private Long id;
    private String name;
    private String email;
    private String majorField;
    private int personalId;
    private Long course_id;

    public TeacherResponse(Teacher teacher) {

    }
}
