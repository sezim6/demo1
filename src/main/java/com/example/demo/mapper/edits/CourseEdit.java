package com.example.demo.mapper.edits;

import com.example.demo.models.Course;
import com.example.demo.response.request.CourseRequest;
import org.springframework.stereotype.Component;

@Component
public class CourseEdit {
    public Course create(CourseRequest request){
        return Course.builder()
                .courseName(request.getCourseName())
                .application(request.getApplication())
                .fees(request.getFees()).build();
    }
    public Course update(CourseRequest request, Course course){
        course =Course.builder()
                .courseName(request.getCourseName())
                .application(request.getApplication())
                .fees(request.getFees()).build();
        return course;
    }
}
