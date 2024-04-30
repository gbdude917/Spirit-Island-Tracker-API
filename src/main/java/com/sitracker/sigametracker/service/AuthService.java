package com.sitracker.sigametracker.service;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<?> registerUser(RegisterDto newUser);

    public ResponseEntity<?> authenticateUser(LoginDto loginDto);

}
