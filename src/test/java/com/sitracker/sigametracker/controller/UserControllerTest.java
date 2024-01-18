package com.sitracker.sigametracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitracker.sigametracker.dto.RegisterDto;
import com.sitracker.sigametracker.dto.UpdatePasswordDto;
import com.sitracker.sigametracker.dto.UpdateUsernameDto;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.EmailAlreadyExistsException;
import com.sitracker.sigametracker.exception.PasswordsDoNotMatchException;
import com.sitracker.sigametracker.repository.UserRepository;
import com.sitracker.sigametracker.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Optional;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.willThrow;
import static org.mockito.Mockito.*;
import static org.springframework.test.util.AssertionErrors.assertNotNull;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    public void init() {
        user = User.Builder.builder()
                .setId(1L)
                .setEmail("build@build.test")
                .setUsername("Bob the Builder")
                .setPassword("test123")
                .build();
    }

    @Test
    public void UserController_getUsers_ReturnListOfUsers() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/v1/users"));

        response.andDo(print()).
                andExpect(status().isOk());
    }

    @Test
    public void UserController_createUser_ReturnCreated() throws Exception {
        RegisterDto mockRegisterDto = new RegisterDto();
        mockRegisterDto.setEmail("build@build.test");
        mockRegisterDto.setUsername("Bob the Builder");
        mockRegisterDto.setPassword("test123");

        ResultActions response = mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockRegisterDto)));

        response.andExpect(status().isCreated());
    }

    @Test
    public void UserController_createUser_ThrowEmailAlreadyExistsException() throws Exception {
        // Mocking the behavior when the email already exists
        when(userService.createUser(any(RegisterDto.class)))
                .thenThrow(new EmailAlreadyExistsException("This email already exists!"));

        RegisterDto mockRegisterDto = new RegisterDto();
        mockRegisterDto.setEmail("email@email.com");
        mockRegisterDto.setUsername("DifferentUser890");
        mockRegisterDto.setPassword("test123");

        ResultActions response = mockMvc.perform(post("/api/v1/users/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockRegisterDto)));

        response.andExpect(status().isConflict());
        response.andExpect(MockMvcResultMatchers.content().string("Email is already in use"));
    }

    @Test
    public void UserController_updateUsername_ReturnsOk() throws Exception {
        // Setup
        UpdateUsernameDto updateUsernameDto = new UpdateUsernameDto();
        updateUsernameDto.setNewUsername("mockUsername");

        // Send data
        ResultActions response = mockMvc.perform(patch("/api/v1/users/update-username/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateUsernameDto)));

        // Assert
        response.andExpect(status().isOk());
        verify(userService).updateUsername(eq(1L), argThat(actualDto ->
                actualDto.getNewUsername().equals(updateUsernameDto.getNewUsername())));
    }

    @Test
    public void UserController_updatePassword_ReturnsOk() throws Exception {
        // Setup
        UpdatePasswordDto updatePasswordDto = new UpdatePasswordDto();
        updatePasswordDto.setOldPassword("password");
        updatePasswordDto.setNewPassword("mockPassword");

        // Send data
        ResultActions response = mockMvc.perform(patch("/api/v1/users/update-password/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updatePasswordDto)));

        // Assert
        response.andExpect(status().isOk());
        verify(userService).updatePassword(eq(1L), argThat(actualDto ->
                actualDto.getNewPassword().equals(updatePasswordDto.getNewPassword())));
    }

    @Test
    public void UserController_updatePassword_ThrowsEntityNotFoundException() throws Exception {
        // Create mock request body
        UpdatePasswordDto updatePasswordDto = new UpdatePasswordDto();
        updatePasswordDto.setOldPassword("mockPassword");
        updatePasswordDto.setNewPassword("newMockPassword");

        // When passed with these parameters, throw EntityNotFoundException
        when(userService.updatePassword(9999L, updatePasswordDto))
                .thenThrow(new EntityNotFoundException("User does not exist"));

        // Call the method and expect that the method throws an EntityNotFoundException
        Exception exception = assertThrows(EntityNotFoundException.class,
                () -> userService.updatePassword(9999L, updatePasswordDto));

        String expectedMessage = "User does not exist";
        String actualMessage = exception.getMessage();

        assertTrue("The messages don't match!", expectedMessage.equals(actualMessage));
    }

}
