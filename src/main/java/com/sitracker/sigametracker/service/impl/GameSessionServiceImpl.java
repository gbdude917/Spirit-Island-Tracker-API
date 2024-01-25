package com.sitracker.sigametracker.service.impl;

import com.sitracker.sigametracker.entity.GameSession;
import com.sitracker.sigametracker.exception.GameSessionNotFoundException;
import com.sitracker.sigametracker.repository.GameSessionRepository;
import com.sitracker.sigametracker.service.GameSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.LocalDate;
import java.util.*;

@Service
public class GameSessionServiceImpl implements GameSessionService {

    private final GameSessionRepository gameSessionRepository;

    @Autowired
    public GameSessionServiceImpl(GameSessionRepository gameSessionRepository) {
        this.gameSessionRepository = gameSessionRepository;
    }

    @Override
    public ResponseEntity<List<GameSession>> getAllGameSessions() {
        return new ResponseEntity<>(gameSessionRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<GameSession> getGameSessionById(Long id) {
        Optional<GameSession> gameSessionOptional = gameSessionRepository.findById(id);

        return gameSessionOptional.map(gameSession -> new ResponseEntity<>(gameSession, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Override
    public ResponseEntity<GameSession> createGameSession(GameSession gameSession) {
        // Validate or preprocess the incoming data if needed
        if (gameSession.getPlayedOn() == null) gameSession.setPlayedOn(LocalDate.now());

        // Save the new game session
        GameSession createdGameSession = gameSessionRepository.save(gameSession);

        return ResponseEntity
                .created(URI.create("/api/v1/game-sessions/" + createdGameSession.getId()))
                .body(createdGameSession);
    }

    @Override
    public ResponseEntity<GameSession> updateGameSession(Long id, GameSession newGameSession) throws Exception {
        Optional<GameSession> gameSessionData = gameSessionRepository.findById(id);

        if (gameSessionData.isEmpty()) throw new GameSessionNotFoundException("Game session not found!");


        GameSession updatedGS = gameSessionData.get();

        updatedGS.setUser(newGameSession.getUser());
        updatedGS.setSpirit(newGameSession.getSpirit());
        updatedGS.setAdversary(newGameSession.getAdversary());
        updatedGS.setBoard(newGameSession.getBoard());
        updatedGS.setSessionName(newGameSession.getSessionName());
        updatedGS.setDescription(newGameSession.getDescription());
        updatedGS.setPlayedOn(newGameSession.getPlayedOn());
        updatedGS.setResult(newGameSession.getResult());
        updatedGS.setIsCompleted(newGameSession.getIsCompleted());

        return new ResponseEntity<>(gameSessionRepository.save(updatedGS), HttpStatus.OK);

    }

    @Override
    public ResponseEntity<HttpStatus> deleteGameSession(Long id) throws Exception {
        if (!gameSessionRepository.existsById(id)) throw new GameSessionNotFoundException("Game session not found!");

        gameSessionRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
