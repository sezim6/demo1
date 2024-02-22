package com.example.demo.service;

import com.example.demo.models.Company;
import com.example.demo.models.Course;
import com.example.demo.rep.CompanyRep;
import com.example.demo.rep.CourseRep;
import com.example.demo.response.CourseResponse;
import com.example.demo.response.request.CourseRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


@Service
@RequiredArgsConstructor
public class CourseService {
    private final CourseRep courseRep;
    private final CompanyRep companyRep;

    public List<CourseResponse> findAll() {
        List<Course> list = courseRep.findAll();
        List<CourseResponse> list1 = new ArrayList<>();
        list.forEach(response -> {
            CourseResponse courseResponse = new CourseResponse(
                    response.getId(),
                    response.getCourseName(),
                    response.getApplication(),
                    response.getFees(),
                    response.getCompany().getId()
            );
            list1.add(courseResponse);
        });
        return list1;
    }

    public CourseResponse findById(Long id) {
        Course course = courseRep.findById(id).orElseThrow();
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getApplication())
                .fees(course.getFees())
                .build();
    }

    public CourseResponse save(String courseName, String application, String fees, Long companyId) {
        Course course = new Course();
        Company company = companyRep.findById(companyId).orElseThrow();
        course.setCourseName(courseName);
        course.setApplication(application);
        course.setFees(fees);
        course.setCompany(company);
        courseRep.save(course);
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .application(course.getApplication())
                .fees(course.getFees())
                .company_id(company.getId())
                .build();
    }

    public CourseResponse update(CourseRequest request, Long id) {
        try {
            if (request == null || request.getCourseName() == null || request.getApplication() == null || request.getFees() == null) {
                throw new IllegalArgumentException("Invalid request data");
            }
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Course course = courseRep.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));
            course.setCourseName(request.getCourseName());
            course.setApplication(request.getApplication());
            course.setFees(request.getFees());
            Course updatedCourse = courseRep.save(course);
            return courseResponse(updatedCourse);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the course: " + e.getMessage(), e);
        }
    }

    private CourseResponse courseResponse(Course course) {
        return new CourseResponse(course);
    }

    public void deleteById(Long id) {
        try {
            courseRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
