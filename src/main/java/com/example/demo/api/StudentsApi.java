package com.example.demo.api;
import com.example.demo.response.StudentResponse;
import com.example.demo.response.request.StudentRequest;
import com.example.demo.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/student")
public class StudentsApi {
    private final StudentService service;
    @PostMapping("save")
    public StudentResponse save(
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String majorField,
            @RequestParam int personalId,
            @RequestParam Long group_id
    ){
        return service.save(name,email,majorField,personalId,group_id);
    }
    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER,TEACHER,STUDENT')")
    public List<StudentResponse> findAll(){
        return service.findAll();
    }
    @GetMapping("find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN, MANAGER,TEACHER,STUDENT')")
    public StudentResponse findById(Long id){
        return service.findById(id);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN, MANAGER')")
    public StudentResponse update(@RequestBody StudentRequest request, @PathVariable Long id){
        return service.update(request,id);
    }
    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,MANAGER')")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}

