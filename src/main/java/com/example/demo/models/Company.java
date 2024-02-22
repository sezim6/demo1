package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.management.relation.Role;
import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String compName;
    private String majors;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Group> groupsList;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL)
    private List<Course> courseList;
}
