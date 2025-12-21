package com.FinproOOPgroupE.Backend.dto;

public class ScoreRequest {
    private String playerId;
    private int score;
    private int coins;
    private int distance;

    public String getPlayerId() { return playerId; }
    public int getScore() { return score; }
    public int getCoins() { return coins; }
    public int getDistance() { return distance; }
}
