package com.sitracker.sigametracker.repository;

import com.sitracker.sigametracker.entity.GameSession;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameSessionRepository extends JpaRepository<GameSession, Long> {
}
