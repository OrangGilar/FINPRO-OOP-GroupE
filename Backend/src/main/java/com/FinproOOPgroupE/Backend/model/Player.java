package com.FinproOOPgroupE.Backend.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "players")
public class Player {

    @Id
    private String playerId;

    @Column(nullable = false, unique = true)
    private String username;

    private int highscore;
    private int totalCoins;
    private int totalDistanceTravelled;
    private LocalDateTime createdAt;

    public Player() {

    }

    public Player(String username) {
        this.playerId = UUID.randomUUID().toString();
        this.username = username;
        this.highscore = 0;
        this.totalCoins = 0;
        this.totalDistanceTravelled = 0;
        this.createdAt = LocalDateTime.now();
    }

    // --- Getters and Setters ---
    public String getPlayerId() { return playerId; }
    public String getUsername() { return username; }
    public int getHighscore() { return highscore; }
    public int getTotalCoins() { return totalCoins; }
    public int getTotalDistanceTravelled() { return totalDistanceTravelled; }

    public void setHighscore(int highscore) { this.highscore = highscore; }
    public void setTotalCoins(int totalCoins) { this.totalCoins = totalCoins; }
    public void setTotalDistanceTravelled(int totalDistanceTravelled) { this.totalDistanceTravelled = totalDistanceTravelled; }
}
