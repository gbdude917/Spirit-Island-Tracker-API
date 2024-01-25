package com.sitracker.sigametracker.controller;

import java.util.*;

import com.sitracker.sigametracker.entity.GameSession;
import com.sitracker.sigametracker.exception.GameSessionNotFoundException;
import com.sitracker.sigametracker.service.impl.GameSessionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/game-sessions")
public class GameSessionController {

    private final GameSessionServiceImpl gameSessionService;

    @Autowired
    public GameSessionController(GameSessionServiceImpl gameSessionService) {
        this.gameSessionService = gameSessionService;
    }

    /**
     * Retrieve all the GameSession entities in the database
     *
     * @return A List of GameSession entities from the database
     */
    @GetMapping
    public ResponseEntity<List<GameSession>> getAllGameSessions() {
        return gameSessionService.getAllGameSessions();
    }

    /**
     * Retrieve a specific game session by id
     *
     * @param id The unique identifier for a game session to be found
     * @return The GameSession entity that matches the id parameter. Otherwise, 404 Not Found is thrown.
     */
    @GetMapping("/{id}")
    public ResponseEntity<GameSession> getGameSessionById(@PathVariable Long id) {
        return gameSessionService.getGameSessionById(id);
    }

    /**
     * Creates and saves the new gameSession to the database
     *
     * @param gameSession The new game session to save
     * @return The newly saved game session
     */
    @PostMapping
    public ResponseEntity<GameSession> createGameSession(@RequestBody GameSession gameSession) {
        return gameSessionService.createGameSession(gameSession);
    }

    /**
     * Deletes a game session matching the id passed in as the parameter
     *
     * @param id The id of the game session to delete from the database
     * @return If id is not found, returns an HttpStatus.NOT_FOUND. Otherwise, returns HttpStatus.NO_CONTENT
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteGameSession(@PathVariable Long id) {
        try {
            return gameSessionService.deleteGameSession(id);
        }
        catch (GameSessionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game session not found!");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
        }

    }

    /**
     * Update the game session matching the id parameter with the new game session's data
     *
     * @param id The id of the original game session in the database to update
     * @param newGameSession The new game session data to replace the original
     * @return If id is not found, returns HttpStatus.NOT_FOUND. Otherwise, the new game session data and HttpStatus.OK
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateGameSession(@PathVariable Long id, @RequestBody GameSession newGameSession) {
        try {
            return gameSessionService.updateGameSession(id, newGameSession);
        }
        catch (GameSessionNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Game session not found!");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something went wrong!");
        }
    }

}
