package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "groups")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String groupName;
    private int amountOfStudent;

    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL)
    private List<Students> students;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "course_group",
            joinColumns = @JoinColumn(name = "group_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private List<Course> courseList;
}
