package com.jskno.iocode.securyty.app.security.service;

import com.jskno.iocode.securyty.app.security.dto.AuthUser;
import com.jskno.iocode.securyty.app.security.dto.RegUser;
import com.jskno.iocode.securyty.app.security.entity.Role;
import com.jskno.iocode.securyty.app.security.entity.User;
import com.jskno.iocode.securyty.app.security.entity.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRepository;

    public User register(RegUser regUser) {
        User user = mapToUser(regUser);
        return userRepository.save(user);
    }

    public String auth(AuthUser authUser) {
        User user = (User) userDetailsService.loadUserByUsername(authUser.getUsername());
        authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authUser.getUsername(), authUser.getPassword()));
        return jwtService.generateJwtToken(authUser.getUsername());
    }

    private User mapToUser(RegUser regUser) {
        return User.builder()
            .username(regUser.getUsername())
            .firstname(regUser.getFirstname())
            .lastname(regUser.getLastname())
            .password(passwordEncoder.encode(regUser.getPassword()))
            .roles(List.of(Role.USER))
            .build();
    }
}
