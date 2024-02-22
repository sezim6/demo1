package com.example.demo.api;
import com.example.demo.response.GroupResponse;
import com.example.demo.response.request.GroupRequest;
import com.example.demo.service.GroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/group")
public class GroupApi {
    private final GroupService groupService;
    @PostMapping("save")
    public GroupResponse save(
            @RequestParam String groupName,
            @RequestParam int amountOfStudent,
            @RequestParam Long company_id,
            @RequestParam List<Long> courseId
    ){
        return groupService.save(groupName,amountOfStudent,company_id,courseId);
    }
    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR,MANAGER')")
    public List<GroupResponse> findAll(){
        return groupService.findAll();
    }
    @GetMapping("find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR,MANAGER')")
    public GroupResponse findById(Long id){
        return groupService.findById(id);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR,MANAGER')")
    public GroupResponse update(@RequestBody GroupRequest request, @PathVariable Long id){
        return groupService.update(request,id);
    }
    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR,MANAGER')")
    public void deleteById(@PathVariable Long id) {
        groupService.deleteById(id);
    }
}