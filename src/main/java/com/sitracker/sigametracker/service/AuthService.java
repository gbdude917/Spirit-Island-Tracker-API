package com.sitracker.sigametracker.service;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> registerUser(RegisterDto newUser);

    ResponseEntity<?> authenticateUser(LoginDto loginDto);

}
