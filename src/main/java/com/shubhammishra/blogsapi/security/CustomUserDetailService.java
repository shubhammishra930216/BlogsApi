package com.shubhammishra.blogsapi.security;

import com.shubhammishra.blogsapi.entiities.User;
import com.shubhammishra.blogsapi.exceptions.ResourceNotFoundException;
import com.shubhammishra.blogsapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      User user = userRepository.findByEmailId(username).orElseThrow(() -> new ResourceNotFoundException("User", "email",username));
      return null;
    }
}


