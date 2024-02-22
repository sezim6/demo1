package com.example.demo.mapper.edits;

import com.example.demo.models.Company;
import com.example.demo.response.request.CompRequest;
import org.springframework.stereotype.Component;

@Component
public class CompEdit {
    public Company create(CompRequest request){
        return Company.builder()
                .compName(request.getCompName())
                .majors(request.getMajors()).build();
    }
    public Company update(CompRequest request, Company company){
        company = Company.builder()
                .compName(request.getCompName())
                .majors(company.getMajors()).build();
        return company;
    }
}
