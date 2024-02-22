package com.example.demo.mapper.edits;

import com.example.demo.models.Students;
import com.example.demo.response.request.StudentRequest;

public class StudEdit {
    public Students create(StudentRequest request){
        return Students.builder()
                .name(request.getName())
                .email(request.getEmail())
                .majorField(request.getMajorField())
                .personalId(request.getPersonalId()).build();
    }
    public Students update(StudentRequest request, Students students){
        students =Students.builder()
                .name(request.getName())
                .email(request.getEmail())
                .majorField(request.getMajorField())
                .personalId(request.getPersonalId()).build();
        return students;
    }
}
