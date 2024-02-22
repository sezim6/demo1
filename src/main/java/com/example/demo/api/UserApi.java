package com.example.demo.api;
import com.example.demo.service.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequiredArgsConstructor
public class UserApi {
    private final UsersService userService;
    @PostMapping("register")
    public String registering(@RequestParam String name , @RequestParam String email, @RequestParam String password){
        return userService.register(name,email, password);
    }
    @PostMapping("login")
    public String login(@RequestParam String email,@RequestParam String password){
        return userService.login(email, password);
    }
}
