package com.example.demo.rep;

import com.example.demo.models.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRep extends JpaRepository<Group,Long> {
}
