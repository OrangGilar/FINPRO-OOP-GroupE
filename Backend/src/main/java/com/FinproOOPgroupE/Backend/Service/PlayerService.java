package com.FinproOOPgroupE.Backend.Service;

import com.FinproOOPgroupE.Backend.Repository.PlayerRepository;
import com.FinproOOPgroupE.Backend.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.FinproOOPgroupE.Backend.dto.PlayerRequest;
import com.FinproOOPgroupE.Backend.dto.ScoreRequest;

import java.util.List;

@Service
public class PlayerService {

    @Autowired
    private PlayerRepository repository;


    public Player registerOrGetPlayer(PlayerRequest request) {
        Player existing = repository.findByUsername(request.getUsername());
        if (existing != null) {
            return existing;
        }
        return repository.save(new Player(request.getUsername()));
    }


    public Player processScoreSubmission(ScoreRequest request) {
        Player player = repository.findById(request.getPlayerId())
                .orElseThrow(() -> new RuntimeException("Player ID not found: " + request.getPlayerId()));

        // 1. Check High Score Logic
        if (request.getScore() > player.getHighscore()) {
            player.setHighscore(request.getScore());
        }

        // 2. Accumulate Stats
        player.setTotalCoins(player.getTotalCoins() + request.getCoins());
        player.setTotalDistanceTravelled(player.getTotalDistanceTravelled() + request.getDistance());

        // 3. Save to DB
        return repository.save(player);
    }

    public List<Player> getLeaderboard() {
        return repository.findTop10ByOrderByHighscoreDesc();
    }

    public List<Player> getAllPlayers() {
        return repository.findAll();
    }

    public Player getPlayerById(String id) {
        return repository.findById(id).orElse(null);
    }
}
