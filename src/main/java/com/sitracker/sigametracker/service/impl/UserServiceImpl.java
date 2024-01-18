package com.sitracker.sigametracker.service.impl;

import java.net.URI;
import java.util.*;

import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.dto.UpdatePasswordDto;
import com.sitracker.sigametracker.dto.UpdateUsernameDto;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.PasswordsDoNotMatchException;
import com.sitracker.sigametracker.exception.UserNotFoundException;
import com.sitracker.sigametracker.repository.UserRepository;
import com.sitracker.sigametracker.exception.UsernameAlreadyExistsException;
import com.sitracker.sigametracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public ResponseEntity<List<User>> getUsers() {
        // TODO: trim out the password
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> getUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        // TODO: trim out the password

        return userOptional.map(user -> new ResponseEntity<>(user, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
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

        return ResponseEntity.created(URI.create("/api/v1/users/" + createdUser.getId())).body(createdUser);
    }

    @Override
    public ResponseEntity<?> updateUsername(Long id, UpdateUsernameDto updateUsernameDto) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        user.setUsername(updateUsernameDto.getNewUsername());

        userRepository.save(user);

        return new ResponseEntity<>("Username updated successfully!", HttpStatus.OK);

    }
    @Override
    public ResponseEntity<?> updatePassword(Long id, UpdatePasswordDto updatePasswordDto) throws Exception {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        // If password old password doesn't equal original, throw an exception
        if (!passwordEncoder.matches(updatePasswordDto.getOldPassword(), user.getPassword())) {
            throw new PasswordsDoNotMatchException("Old password does not match!");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordDto.getNewPassword()));

        userRepository.save(user);

        return new ResponseEntity<>("Password updated successfully!", HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> deleteUser(Long id) throws Exception {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException("This user does not exist! Cannot delete user.");
        }

        userRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
