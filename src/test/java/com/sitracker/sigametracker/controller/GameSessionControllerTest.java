package com.sitracker.sigametracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sitracker.sigametracker.entity.Adversary;
import com.sitracker.sigametracker.entity.GameSession;
import com.sitracker.sigametracker.entity.Spirit;
import com.sitracker.sigametracker.entity.User;
import com.sitracker.sigametracker.exception.GameSessionNotFoundException;
import com.sitracker.sigametracker.repository.GameSessionRepository;
import com.sitracker.sigametracker.service.impl.GameSessionServiceImpl;
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

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GameSessionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class GameSessionControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GameSessionServiceImpl gameSessionServiceImpl;

    @MockBean
    private GameSessionRepository gameSessionRepository;

    @MockBean
    private UserController userController;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void GameSessionController_getGameSessions_ReturnListOfGameSessions() throws Exception {
        ResultActions response = mockMvc.perform(get("/api/v1/game-sessions"));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void GameSessionController_getGameSessionById_ReturnGameSession() throws Exception {
        Long gameSessionId = 1L;

        ResultActions response = mockMvc.perform(get("/api/v1/game-sessions/{id}", gameSessionId));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void GameSessionController_getGameSessionById_ReturnsNotFound() throws Exception {
        when(gameSessionServiceImpl.getGameSessionById(9999L)).thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));

        mockMvc.perform(get("/api/v1/game-sessions/{id}", 9999L))
                .andExpect(status().isNotFound());
    }

    @Test
    public void GameSessionController_createUser_ReturnsCreated() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        Spirit mockSpirit = new Spirit();
        mockSpirit.setId(2L);

        Adversary mockAdversary = new Adversary();
        mockAdversary.setId(3L);

        GameSession mockGameSession = new GameSession();
        mockGameSession.setUser(mockUser); // Assuming 1L is a valid user ID
        mockGameSession.setSpirit(mockSpirit); // Assuming 2L is a valid spirit ID
        mockGameSession.setAdversary(mockAdversary); // Assuming 3L is a valid adversary ID
        mockGameSession.setBoard("SA");
        mockGameSession.setSessionName("Sample Session");
        mockGameSession.setDescription("Sample Description");
        mockGameSession.setPlayedOn(LocalDate.now());
        mockGameSession.setResult("Sample Result");
        mockGameSession.setIsCompleted(true);

        ResultActions response = mockMvc.perform(post("/api/v1/game-sessions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockGameSession)));
    }

    @Test
    public void GameSessionController_deleteGameSession_ReturnsHttpNoContent() throws Exception {
        when(gameSessionServiceImpl.deleteGameSession(1L))
                .thenReturn(new ResponseEntity<>(HttpStatus.NO_CONTENT));

        ResultActions response = mockMvc.perform(delete("/api/v1/game-sessions/{id}", 1L));

        response.andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    public void UserController_deleteUser_ThrowsUserNotFoundException() throws Exception {
        // When userService.deleteUser is called with parameter 1L, return a ResponseEntity with HttpStatus.NO_CONTENT
        when(gameSessionServiceImpl.deleteGameSession(9999L))
                .thenThrow(new GameSessionNotFoundException("Game session not found!"));

        // Call the method and expect that the method throws an EntityNotFoundException
        Exception exception = assertThrows(GameSessionNotFoundException.class,
                () -> gameSessionServiceImpl.deleteGameSession(9999L));

        String expectedMessage = "Game session not found!";
        String actualMessage = exception.getMessage();

        assertTrue("The messages don't match!", expectedMessage.equals(actualMessage));
    }

    @Test
    public void GameSessionController_updateGameSession_ReturnsOk() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        Spirit mockSpirit = new Spirit();
        mockSpirit.setId(2L);

        Adversary mockAdversary = new Adversary();
        mockAdversary.setId(3L);

        GameSession mockNewGameSession = new GameSession();
        mockNewGameSession.setUser(mockUser); // Assuming 1L is a valid user ID
        mockNewGameSession.setSpirit(mockSpirit); // Assuming 2L is a valid spirit ID
        mockNewGameSession.setAdversary(mockAdversary); // Assuming 3L is a valid adversary ID
        mockNewGameSession.setBoard("SA");
        mockNewGameSession.setSessionName("New Sample Session");
        mockNewGameSession.setDescription("New Sample Description");
        mockNewGameSession.setPlayedOn(LocalDate.now());
        mockNewGameSession.setResult("New Result");
        mockNewGameSession.setIsCompleted(true);

        ResultActions response = mockMvc.perform(put("/api/v1/game-sessions/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(mockNewGameSession)));

        response.andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void GameSessionController_updateGameSession_ReturnsNotFound() throws Exception {
        User mockUser = new User();
        mockUser.setId(1L);

        Spirit mockSpirit = new Spirit();
        mockSpirit.setId(2L);

        Adversary mockAdversary = new Adversary();
        mockAdversary.setId(3L);

        GameSession mockNewGameSession = new GameSession();
        mockNewGameSession.setUser(mockUser); // Assuming 1L is a valid user ID
        mockNewGameSession.setSpirit(mockSpirit); // Assuming 2L is a valid spirit ID
        mockNewGameSession.setAdversary(mockAdversary); // Assuming 3L is a valid adversary ID
        mockNewGameSession.setBoard("SA");
        mockNewGameSession.setSessionName("New Sample Session");
        mockNewGameSession.setDescription("New Sample Description");
        mockNewGameSession.setPlayedOn(LocalDate.now());
        mockNewGameSession.setResult("New Result");
        mockNewGameSession.setIsCompleted(true);

        // When userService.deleteUser is called with parameter 1L, return a ResponseEntity with HttpStatus.NO_CONTENT
        when(gameSessionServiceImpl.updateGameSession(9999L, mockNewGameSession))
                .thenThrow(new GameSessionNotFoundException("Game session not found!"));

        // Call the method and expect that the method throws an EntityNotFoundException
        Exception exception = assertThrows(GameSessionNotFoundException.class,
                () -> gameSessionServiceImpl.updateGameSession(9999L, mockNewGameSession));

        String expectedMessage = "Game session not found!";
        String actualMessage = exception.getMessage();

        assertTrue("The messages don't match!", expectedMessage.equals(actualMessage));

    }
}
