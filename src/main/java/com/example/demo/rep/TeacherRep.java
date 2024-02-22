package com.example.demo.rep;

import com.example.demo.models.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherRep extends JpaRepository<Teacher,Long> {
}
