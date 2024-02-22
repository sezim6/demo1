package com.example.demo.service;
import com.example.demo.models.Group;
import com.example.demo.models.Students;
import com.example.demo.rep.GroupRep;
import com.example.demo.rep.StudentsRep;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.request.StudentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentsRep studentsRep;
    private final GroupRep groupRep;
    public List<StudentResponse> findAll() {
        List <Students> list = studentsRep.findAll();
        List<StudentResponse> list1 = new ArrayList<>();
        list.forEach(response -> {
            StudentResponse studentResponse = new StudentResponse(
                    response.getId(),
                    response.getName(),
                    response.getEmail(),
                    response.getMajorField(),
                    response.getPersonalId(),
                    response.getGroups().getId()
            );
            list1.add(studentResponse);
        });
        return list1;
    }
    public StudentResponse findById(Long id) {
        Students students = studentsRep.findById(id).orElseThrow();
        return StudentResponse.builder()
                .id(students.getId())
                .name(students.getName())
                .email(students.getEmail())
                .majorField(students.getMajorField())
                .personalId(students.getPersonalId())
                .group_id(students.getGroups().getId())
                .build();
    }
    public StudentResponse save(String name , String email, String majorField, int personalId, Long group_id){
        Students students = new Students();
        Group group = groupRep.findById(group_id).orElseThrow();
        students.setName(name);
        students.setEmail(email);
        students.setMajorField(majorField);
        students.setPersonalId(personalId);
        students.setGroups(group);
        studentsRep.save(students);
        return StudentResponse.builder()
                .id(students.getId())
                .name(students.getName())
                .email(students.getEmail())
                .majorField(students.getMajorField())
                .personalId(students.getPersonalId())
                .group_id(students.getGroups().getId()).build();
    }
    public StudentResponse update(StudentRequest request, Long id) {
        try {
            if (request == null || request.getName() == null || request.getEmail()==null || request.getMajorField()==null){throw new IllegalArgumentException("Invalid request data");
            }
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Students students = studentsRep.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));
            students.setName(request.getName());
            students.setEmail(request.getEmail());
            students.setMajorField(request.getMajorField());
            students.setPersonalId(request.getPersonalId());
            Students updateStudent = studentsRep.save(students);

            return studentResponse(updateStudent);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the course: " + e.getMessage(), e);
        }
    }
    private StudentResponse studentResponse(Students students) {
        return new StudentResponse(students);
    }
    public void deleteById(Long id) {
        try {
            studentsRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
