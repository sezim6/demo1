package com.example.demo.mapper.views;

import com.example.demo.models.Company;
import com.example.demo.response.CompResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class CompView {
    public CompResponse map(Company company){
        return CompResponse.builder()
                .id(company.getId())
                .compName(company.getCompName())
                .majors(company.getMajors()).build();
    }
    public List<CompResponse> map(List<Company>companies){
        List<CompResponse> list = new ArrayList<>();
        for (Company company: companies){
            list.add(map(company));
        }
        return list;
    }
}
