package com.example.demo.mapper.views;

import com.example.demo.models.Course;
import com.example.demo.response.CourseResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CourseView {
    public CourseResponse map(Course course){
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .application(course.getApplication())
                .fees(course.getFees()).build();
    }
    public List<CourseResponse> map(List<Course >courses){
        List<CourseResponse> list = new ArrayList<>();
        for (Course course: courses){
            list.add(map(course));
        }
        return list;
    }
}
