package com.example.demo.api;
import com.example.demo.response.CompResponse;
import com.example.demo.response.request.CompRequest;
import com.example.demo.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/v1/company")
public class CompanyApi {
    private final CompanyService companyService;

    @GetMapping("findAll")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public List<CompResponse> findAll() {
        return companyService.findAll();
    }

    @GetMapping("find/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public CompResponse findById(Long id) {
        return companyService.findById(id);
    }


    @PostMapping("save")
    public CompResponse save(
            @RequestParam String compName,
            @RequestParam String majors
    ) {
        return companyService.save(compName, majors);
    }
    @PutMapping("update/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public CompResponse update(@RequestBody CompRequest request, @PathVariable Long id){
        return companyService.update(request,id);
    }
    @DeleteMapping("/delete/by/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN,DIRECTOR')")
    public void deleteById(@PathVariable Long id) {
        companyService.deleteById(id);
    }
}


