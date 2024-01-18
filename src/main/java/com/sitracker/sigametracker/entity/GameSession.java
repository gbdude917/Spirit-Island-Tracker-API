package com.sitracker.sigametracker.entity;

import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "game_sessions")
public class GameSession {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "spirit_id", nullable = false)
    private Spirit spirit;

    @ManyToOne
    @JoinColumn(name = "adversary_id", nullable = false)
    private Adversary adversary;

    @Column(nullable = false)
    private String board;

    @Column(name = "session_name", nullable = false)
    private String sessionName;

    @Column(nullable = false)
    private String description;

    @Column(name = "played_on")
    private LocalDate playedOn;

    private String result;

    private boolean isCompleted;

    /** Getters **/
    public Long getId() { return id; }

    public User getUser() { return user; }

    public Spirit getSpirit() { return spirit; }

    public Adversary getAdversary() { return adversary; }

    public String getBoard() { return board; }

    public String getSessionName() { return sessionName; }

    public String getDescription() { return description; }

    public LocalDate getPlayedOn() { return playedOn; }

    public String getResult() { return result; }

    public boolean getIsCompleted() { return isCompleted; }

    /** Setters **/
//    public void setId(Long id) { this.id = id; }

    public void setUser(User user) { this.user = user; }

    public void setSpirit(Spirit spirit) { this.spirit = spirit; }

    public void setAdversary(Adversary adversary) { this.adversary = adversary; }

    public void setBoard(String board) { this.board = board; }

    public void setSessionName(String sessionName) { this.sessionName = sessionName; }

    public void setDescription(String description) { this.description = description; }

    public void setPlayedOn(LocalDate playedOn) { this.playedOn = playedOn; }

    public void setResult(String result) { this.result = result; }

    public void setIsCompleted(boolean isCompleted) { this.isCompleted = isCompleted; }
}
