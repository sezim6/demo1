package com.example.demo.rep;

import com.example.demo.models.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface AllRepository extends JpaRepository <Users,Long> {

    boolean existsByEmail(String email);

    Users findByEmail(String email);
}
