package com.sitracker.sigametracker.controller;

import java.util.*;

import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.dto.UpdatePasswordDto;
import com.sitracker.sigametracker.dto.UpdateUsernameDto;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.PasswordsDoNotMatchException;
import com.sitracker.sigametracker.exception.UsernameAlreadyExistsException;
import com.sitracker.sigametracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        return userService.getUsers();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PatchMapping("/update-username/{id}")
    public ResponseEntity<?> updateUsername(@PathVariable Long id, @RequestBody UpdateUsernameDto updateUsernameDto) {
        return userService.updateUsername(id, updateUsernameDto);
    }

    @PatchMapping("/update-password/{id}")
    public ResponseEntity<?> updatePassword(@PathVariable Long id, @RequestBody UpdatePasswordDto updatePasswordDto) {
        try {
            return userService.updatePassword(id, updatePasswordDto);
        }
        catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found!");
        }
        catch (PasswordsDoNotMatchException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Old password does not match original!");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Something went wrong!");
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            return userService.deleteUser(id);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Something went wrong. Cannot delete user.");
        }
    }
}
