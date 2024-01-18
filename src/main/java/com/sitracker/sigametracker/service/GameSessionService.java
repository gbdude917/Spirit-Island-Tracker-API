package com.sitracker.sigametracker.service;

import com.sitracker.sigametracker.entity.GameSession;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

public interface GameSessionService {

    ResponseEntity<List<GameSession>> getAllGameSessions();

    ResponseEntity<GameSession> getGameSessionById(Long id);

    ResponseEntity<GameSession> createGameSession(GameSession gameSession);

    ResponseEntity<GameSession> updateGameSession(Long id, GameSession newGameSession);

    ResponseEntity<HttpStatus> deleteGameSession(Long id);

}
