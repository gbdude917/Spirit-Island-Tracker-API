package com.sitracker.sigametracker.service;

import java.util.*;

import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.dto.UpdateUsernameDto;
import com.sitracker.sigametracker.dto.UpdatePasswordDto;
import com.sitracker.sigametracker.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    ResponseEntity<List<User>> getUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<?> updateUsername(Long id, UpdateUsernameDto updateUsernameDto);

    ResponseEntity<?> updatePassword(Long id, UpdatePasswordDto updatePasswordDto) throws Exception;

    ResponseEntity<?> deleteUser(Long id) throws Exception;
}
