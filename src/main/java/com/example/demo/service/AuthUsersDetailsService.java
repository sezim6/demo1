package com.example.demo.service;

import com.example.demo.rep.AllRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUsersDetailsService implements UserDetailsService {
    private final AllRepository rep;
    @Override
    public UsersDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new UsersDetails(rep.findByEmail(username));
    }
}
