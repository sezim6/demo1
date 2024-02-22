package com.example.demo.api;
import com.example.demo.response.TeacherResponse;
import com.example.demo.response.request.TeacherRequest;
import com.example.demo.service.TeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/teacher")
public class TeacherApi {
    private final TeacherService teacherService;
    @PostMapping("save")
    public TeacherResponse save(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String majorField,
            @RequestParam int personalId,
            @RequestParam Long course_id
    ){
        return teacherService.save(name,email,majorField,personalId,course_id);
    }
    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER,TEACHER')")
    public List<TeacherResponse> findAll(){
        return teacherService.findAll();
    }
    @GetMapping("find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER,TEACHER')")
    public TeacherResponse findById(Long id){
        return teacherService.findById(id);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER')")
    public TeacherResponse update(@RequestBody TeacherRequest request, @PathVariable Long id){
        return teacherService.update(request,id);
    }
    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER')")
    public void deleteById(@PathVariable Long id) {
        teacherService.deleteById(id);
    }
}


