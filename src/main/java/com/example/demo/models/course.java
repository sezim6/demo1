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
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String courseName;
    private String application;
    private String fees;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @OneToOne(mappedBy = "course", cascade = CascadeType.ALL)
    private Teacher teachers;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "courseList")
    private List<Group> group;

}
