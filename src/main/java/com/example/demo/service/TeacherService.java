package com.example.demo.service;
import com.example.demo.models.Course;
import com.example.demo.models.Teacher;
import com.example.demo.rep.CourseRep;
import com.example.demo.rep.TeacherRep;
import com.example.demo.response.TeacherResponse;
import com.example.demo.response.request.TeacherRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TeacherService {
    private final TeacherRep teacherRep;
    private final CourseRep courseRep;
    public List<TeacherResponse> findAll() {
        List <Teacher> list = teacherRep.findAll();
        List<TeacherResponse> list1 = new ArrayList<>();
        list.forEach(response -> {
            TeacherResponse teacherResponse = new TeacherResponse(
                    response.getId(),
                    response.getName(),
                    response.getEmail(),
                    response.getMajorField(),
                    response.getPersonalId(),
                    response.getCourse().getId()
            );
            list1.add(teacherResponse);
        });
        return list1;
    }
    public TeacherResponse findById(Long id) {
        Teacher teacher = teacherRep.findById(id).orElseThrow();
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .personalId(teacher.getPersonalId())
                .majorField(teacher.getMajorField())
                .course_id(teacher.getCourse().getId())
                .build();
    }
    public TeacherResponse save(String name , String email, String majorField, int personalId, Long course_id){
        Teacher teacher = new Teacher();
        Course course = courseRep.findById(course_id).orElseThrow();
        teacher.setName(name);
        teacher.setEmail(email);
        teacher.setMajorField(majorField);
        teacher.setPersonalId(personalId);
        teacher.setCourse(course);
        teacherRep.save(teacher);
        return TeacherResponse.builder()
                .id(teacher.getId())
                .name(teacher.getName())
                .email(teacher.getEmail())
                .majorField(teacher.getMajorField())
                .personalId(teacher.getPersonalId())
                .course_id(teacher.getCourse().getId())
                .build();
    }
    public TeacherResponse update(TeacherRequest request, Long id) {
        try {
            if (request == null || request.getName() == null || request.getEmail() == null || request.getMajorField() == null) {
                throw new IllegalArgumentException("Invalid request data");
            }
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Teacher teacher = teacherRep.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));
            teacher.setName(request.getName());
            teacher.setEmail(request.getEmail());
            teacher.setMajorField(request.getMajorField());
            teacher.setPersonalId(request.getPersonalId());
            Teacher updateteacher = teacherRep.save(teacher);

            return teacherResponse(updateteacher);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the course: " + e.getMessage(), e);
        }
    }
    private TeacherResponse teacherResponse(Teacher teacher) {
        return new TeacherResponse(teacher);
    }
    public void deleteById(Long id) {
        try {
            teacherRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
