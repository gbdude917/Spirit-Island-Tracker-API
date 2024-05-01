package com.sitracker.sigametracker.controller;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.service.AuthService;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.UsernameAlreadyExistsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authSerivce;

    @Autowired
    public AuthController(AuthService authService) { this.authSerivce = authService; }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?> createUser(@RequestBody RegisterDto registerDto) {
        try {
            return authSerivce.registerUser(registerDto);
        }
        catch(EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email is already in use");
        }
        catch(UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username is already in use");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto loginDto) {
        return authSerivce.authenticateUser(loginDto);
    }
}
