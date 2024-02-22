package com.example.demo.service;

import com.example.demo.models.Company;
import com.example.demo.models.Course;
import com.example.demo.models.Group;
import com.example.demo.rep.CompanyRep;
import com.example.demo.rep.CourseRep;
import com.example.demo.rep.GroupRep;
import com.example.demo.response.GroupResponse;
import com.example.demo.response.request.GroupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRep groupRep;
    private final CompanyRep companyRep;
    private final CourseRep courseRep;

    public List<GroupResponse> findAll() {
        List<Group> list = groupRep.findAll();
        List<GroupResponse> list1 = new ArrayList<>();
        list.forEach(response -> {
            GroupResponse groupResponse = new GroupResponse(
                    response.getId(),
                    response.getGroupName(),
                    response.getAmountOfStudent(),
                    response.getCompany().getId()
            );
            list1.add(groupResponse);
        });
        return list1;
    }

    public GroupResponse findById(Long id) {
        Group group = groupRep.findById(id).orElseThrow();
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .amountOfStudent(group.getAmountOfStudent())
                .company_id(group.getCompany().getId())
                .build();
    }

    public GroupResponse save(String groupName, int amountOfStudent, Long company_id, List<Long> courseId) {
        List<Course> list = courseRep.findAllById(courseId);
        Group group = new Group();
        group.setCourseList(list);
        Company company = companyRep.findById(company_id).orElseThrow();
        group.setGroupName(groupName);
        group.setAmountOfStudent(amountOfStudent);
        group.setCompany(company);
        groupRep.save(group);
        return GroupResponse.builder()
                .id(group.getId())
                .groupName(group.getGroupName())
                .amountOfStudent(group.getAmountOfStudent())
                .company_id(group.getCompany().getId()).build();
    }

    public GroupResponse update(GroupRequest request, Long id) {
        try {
            if (request == null || request.getGroupName() == null) {
                throw new IllegalArgumentException("Invalid request data");
            }
            if (id == null) {
                throw new IllegalArgumentException("ID cannot be null");
            }
            Group group = groupRep.findById(id)
                    .orElseThrow(() -> new NoSuchElementException("Course not found with ID: " + id));
            group.setGroupName(request.getGroupName());
            group.setAmountOfStudent(request.getAmountOfStudent());
            Group updateGroup = groupRep.save(group);

            return groupResponse(updateGroup);
        } catch (IllegalArgumentException | NoSuchElementException e) {
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("An error occurred while updating the course: " + e.getMessage(), e);
        }
    }

    private GroupResponse groupResponse(Group group) {
        return new GroupResponse(group);
    }

    public void deleteById(Long id) {
        try {
            groupRep.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new NoSuchElementException("Course not found with ID: " + id);
        }
    }
}
