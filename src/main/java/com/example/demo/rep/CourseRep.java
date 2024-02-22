package com.example.demo.rep;

import com.example.demo.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRep extends JpaRepository<Course,Long > {
}
