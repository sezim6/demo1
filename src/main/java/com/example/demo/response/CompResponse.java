package com.example.demo.response;

import com.example.demo.models.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompResponse {
    private Long id;
    private String compName;
    private String majors;

    public CompResponse(Company company) {

    }
}
