package com.sitracker.sigametracker.service.impl;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.PasswordsDoNotMatchException;
import com.sitracker.sigametracker.exception.UserNotFoundException;
import com.sitracker.sigametracker.exception.UsernameAlreadyExistsException;
import com.sitracker.sigametracker.repository.UserRepository;
import com.sitracker.sigametracker.service.AuthService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
//import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;

@Service
public class AuthServiceImpl implements AuthService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public ResponseEntity<User> createUser(RegisterDto newUser) {
        // Check if email exists in database
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException("This email already exists! Login with the associated username");
        }

        // Check if username exists in database
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistsException("Username: " + newUser.getUsername() + " already exists! Choose a new username or login with that username.");
        }

        // Create the new User object
        User createdUser = new User();
        createdUser.setUsername(newUser.getUsername());
        createdUser.setEmail(newUser.getEmail());

        // TODO: validate the password

        createdUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        userRepository.save(createdUser);

        return ResponseEntity.created(URI.create("/api/v1/users/" + createdUser.getId())).body(createdUser);    }

    // TODO: Fix this implementation to use Spring Security and pass a JWT token to frontend
    public ResponseEntity<String> authenticateUser(LoginDto loginDto) {
        try {
            // Check if username is correct
            if (!userRepository.existsByUsername(loginDto.getUsername())){
                throw new Exception("User/Password is incorrect");
            }

            User user = userRepository.findByUsername(loginDto.getUsername());

            // Password validation
            if (!passwordEncoder.matches(loginDto.getPassword(), user.getPassword())) {
                throw new Exception("User/Password is incorrect");
            }

            return ResponseEntity.status(HttpStatus.OK).body("Successfully logged in");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User/Password is incorrect");
        }
    }
}
