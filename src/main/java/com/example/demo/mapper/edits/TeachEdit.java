package com.example.demo.mapper.edits;
import com.example.demo.models.Teacher;
import com.example.demo.response.request.TeacherRequest;
import org.springframework.stereotype.Component;

@Component
public class TeachEdit {
    public Teacher create(TeacherRequest request){
        return Teacher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .majorField(request.getMajorField()).
                personalId(request.getPersonalId()).build();
    }
    public Teacher update(TeacherRequest request, Teacher teacher){
        teacher =Teacher.builder()
                .name(request.getName())
                .email(request.getEmail())
                .majorField(request.getMajorField())
                .personalId(request.getPersonalId()).build();
        return teacher;
    }
}
