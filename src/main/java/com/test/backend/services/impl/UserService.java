package com.test.backend.services.impl;


import com.test.backend.entities.DTO.AuthResponseDto;
import com.test.backend.entities.DTO.LoginDto;
import com.test.backend.entities.User;
import com.test.backend.repositories.UserRepository;
import com.test.backend.security.JWTGenerator;
import com.test.backend.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JWTGenerator jwtGenerator;
    @Autowired
    PasswordEncoder passwordEncoder;

    @Override
    public String addUser(User userEntity) {
        if (userRepository.existsByEmail(userEntity.getEmail())) {
            return "User with this email already exists";
        }

        User user = new User();
        user.setFirstname(userEntity.getFirstname());
        user.setLastname(userEntity.getLastname());
        user.setEmail(userEntity.getEmail());
        user.setPassword(passwordEncoder.encode((userEntity.getPassword())));
        user.setAddress(userEntity.getAddress());
        user.setRole(userEntity.getRole());
        userRepository.save(user);

        return "User registered successfully!";
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public AuthResponseDto login(@RequestBody LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());


        if (user == null ) {
            return null;
        }
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getEmail(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);


        return new AuthResponseDto(token,user.get().getID());
    }
}
