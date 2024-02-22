package com.example.demo.api;

import com.example.demo.rep.CourseRep;
import com.example.demo.response.CourseResponse;
import com.example.demo.response.request.CourseRequest;
import com.example.demo.service.CourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/course")
public class CourseApi {
    private final CourseService courseService;

    @PostMapping("save")
    public CourseResponse save(
            @RequestParam String courseName,
            @RequestParam String application,
            @RequestParam String fees,
            @RequestParam Long company_id
    ) {
        return courseService.save(courseName, application, fees, company_id);
    }

    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public List<CourseResponse> findAll() {
        return courseService.findAll();
    }

    @GetMapping("find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public CourseResponse findById(Long id) {
        return courseService.findById(id);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public CourseResponse update(@RequestBody CourseRequest request, @PathVariable Long id) {
        return courseService.update(request, id);
    }

    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public void deleteById(@PathVariable Long id) {
        courseService.deleteById(id);
    }
}
