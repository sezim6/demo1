package com.example.demo.rep;

import com.example.demo.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRep extends JpaRepository<Company, Long > {

}
