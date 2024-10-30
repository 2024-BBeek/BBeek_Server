package com.bbeek.server.domain.auth.service;

import com.bbeek.server.domain.auth.service.dto.CustomUserDetails;
import com.bbeek.server.domain.user.domain.User;
import com.bbeek.server.domain.user.domain.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {

        User user = userRepository.findByUserId(userId);

        if (user != null) {

            return new CustomUserDetails(user);
        }

        return null;
    }
}
