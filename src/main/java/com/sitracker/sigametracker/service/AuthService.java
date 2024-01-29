package com.sitracker.sigametracker.service;

import com.sitracker.sigametracker.dto.LoginDto;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.entity.User;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    public ResponseEntity<User> createUser(RegisterDto newUser);

    public ResponseEntity<String> authenticateUser(LoginDto loginDto);

}
