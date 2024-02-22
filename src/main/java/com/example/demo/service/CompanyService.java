package com.example.demo.service;

import com.example.demo.models.Company;
import com.example.demo.models.Course;
import com.example.demo.rep.CompanyRep;
import com.example.demo.response.CompResponse;
import com.example.demo.response.CourseResponse;
import com.example.demo.response.request.CompRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final CompanyRep companyRep;

    public List<CompResponse> findAll() {
        List <Company> list = companyRep.findAll();
        List<CompResponse> list1 = new ArrayList<>();
        list.forEach(response -> {
            CompResponse compResponse = new CompResponse(
                response.getId(),
                response.getCompName(),
                response.getMajors()
        );
            list1.add(compResponse);
        });
        return list1;
    }

    public CompResponse findById(Long id) {
        Company company = companyRep.findById(id).orElseThrow();
        return CompResponse.builder()
                .id(company.getId())
                .compName(company.getCompName())
                .majors(company.getMajors())
                .build();
    }

    public CompResponse save(String name, String major) {
        Company company = new Company();
        company.setCompName(name);
        company.setMajors(major);
        companyRep.save(company);
        return CompResponse.builder()
                .id(company.getId())
                .compName(company.getCompName())
                .majors(company.getMajors())
                .build();
    }
    public CompResponse update(CompRequest request, Long id) {
        try {
            if (request == null || request.getCompName() == null || request.getMajors() == null ) {
                throw new IllegalArgumentException("Invalid request data");
            }
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Company company = companyRep.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));
            company.setCompName(request.getCompName());
            company.setMajors(request.getMajors());
            Company updatedCompany = companyRep.save(company);

            return response(updatedCompany);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the course: " + e.getMessage(), e);
        }
    }
    private CompResponse response(Company company) {
        return new CompResponse(company);
    }
    public void deleteById(Long id) {
        try {
            companyRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
