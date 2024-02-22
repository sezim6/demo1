package com.example.demo.mapper.views;
import com.example.demo.models.Teacher;
import com.example.demo.response.TeacherResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class TeachView {
    public TeacherResponse map(Teacher teacher){
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .majorField(teacher.getMajorField())
                .personalId(teacher.getPersonalId()).build();
    }
    public List<TeacherResponse> map(List<Teacher >teachers){
        List<TeacherResponse> list = new ArrayList<>();
        for (Teacher teacher : teachers){
            list.add(map(teacher));
        }
        return list;
    }
}
