package com.sitracker.sigametracker.service;

import java.util.*;

import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.dto.UpdateUsernameDto;
import com.sitracker.sigametracker.dto.UpdatePasswordDto;
import com.sitracker.sigametracker.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public interface UserService {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> createUser(RegisterDto newUser);

    ResponseEntity<?> updateUsername(Long id, UpdateUsernameDto updateUsernameDto);

    ResponseEntity<?> updatePassword(Long id, UpdatePasswordDto updatePasswordDto) throws Exception;

    ResponseEntity<HttpStatus> deleteUser(Long id);
}
