package com.example.demo.rep;

import com.example.demo.models.Students;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentsRep extends JpaRepository<Students,Long > {
}
