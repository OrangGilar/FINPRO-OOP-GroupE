package com.FInpro.OOP.GroupE.Models;

public class LeaderboardEntry implements Comparable<LeaderboardEntry> {
    public String username;
    public int score;

    public LeaderboardEntry(String username, int score){
        this.username = username;
        this.score = score;
    }
    @Override
    public int compareTo(LeaderboardEntry other){
        return other.score - this.score;
    }
}
