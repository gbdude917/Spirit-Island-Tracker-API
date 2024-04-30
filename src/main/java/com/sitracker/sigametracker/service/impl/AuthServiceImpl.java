package com.sitracker.sigametracker.service.impl;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.UsernameAlreadyExistsException;
import com.sitracker.sigametracker.repository.UserRepository;
import com.sitracker.sigametracker.security.JwtResponse;
import com.sitracker.sigametracker.service.AuthService;
import com.sitracker.sigametracker.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Date;

@Service
public class AuthServiceImpl implements AuthService {

    public final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    private JwtService jwtService;


    @Autowired
    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           AuthenticationManager authenticationManager,
                           JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    public ResponseEntity<?> registerUser(RegisterDto newUser) {
        // Check if email exists in database
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new EmailAlreadyExistsException("This email already exists! Login with the associated username");
        }

        // Check if username exists in database
        if (userRepository.existsByUsername(newUser.getUsername())) {
            throw new UsernameAlreadyExistsException("Username: " + newUser.getUsername() + " already exists! Choose a new username or login with that username.");
        }

        // Create the new User object
        ResponseEntity<?> response = null;
        try {
            User createdUser = new User();
            createdUser.setUsername(newUser.getUsername());
            createdUser.setEmail(newUser.getEmail());
            createdUser.setRegistrationDate(new Date());
            createdUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

            userRepository.save(createdUser);

            response = ResponseEntity.created(URI.create("/api/v1/users/" + createdUser.getId())).body(createdUser);
        } catch (Exception e) {
            response = ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An exception occured due to " + e.getMessage());
        }

        return response;
    }

    // TODO: Fix this implementation to use Spring Security and pass a JWT token to frontend
    public ResponseEntity<?> authenticateUser(LoginDto loginDto) {
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

            // Authenticate the user
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword())
            );

            // Set the authentication in the SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generate JWT token
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();
            String jwtToken = jwtService.generateToken(userDetails.getUsername());

            return ResponseEntity.ok(new JwtResponse(jwtToken));
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("UNAUTHORIZED: " + e.getMessage());
        }
    }
}
