package com.example.demo.mapper.views;

import com.example.demo.models.Students;
import com.example.demo.response.StudentResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class StudView {
    public StudentResponse map(Students students){
        return StudentResponse.builder()
                .id(students.getId())
                .name(students.getName())
                .email(students.getEmail())
                .majorField(students.getMajorField())
                .personalId(students.getPersonalId()).build();
    }
    public List<StudentResponse> map(List<Students >students){
        List<StudentResponse> list = new ArrayList<>();
        for (Students student : students){
            list.add(map(student));
        }
        return list;
    }
}
