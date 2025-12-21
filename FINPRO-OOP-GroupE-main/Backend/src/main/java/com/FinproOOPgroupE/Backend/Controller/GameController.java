package com.FinproOOPgroupE.Backend.Controller;

import com.FinproOOPgroupE.Backend.model.Player;
import com.FinproOOPgroupE.Backend.Repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private PlayerRepository repository;

    // 1. REGISTER PLAYER
    // POST http://localhost:8080/api/players
    @PostMapping("/players")
    public Player createPlayer(@RequestBody Map<String, String> body) {
        String username = body.get("username");

        Player existing = repository.findByUsername(username);
        if (existing != null) {
            return existing; // Return existing player if found
        }

        // Create new player
        return repository.save(new Player(username));
    }

    // 2. SUBMIT SCORE
    // POST http://localhost:8080/api/scores
    @PostMapping("/scores")
    public Player updateScore(@RequestBody Map<String, Object> body) {
        String id = (String) body.get("playerId");
        int score = (int) body.get("score");
        int coins = (int) body.get("coins");
        int distance = (int) body.get("distance");

        Player player = repository.findById(id).orElseThrow(() -> new RuntimeException("Player not found"));

        // Update Highscore only if the new score is better
        if (score > player.getHighscore()) {
            player.setHighscore(score);
        }

        // Add coins and distance to total
        player.setTotalCoins(player.getTotalCoins() + coins);
        player.setTotalDistanceTravelled(player.getTotalDistanceTravelled() + distance);

        return repository.save(player);
    }

    // 3. GET LEADERBOARD
    // GET http://localhost:8080/api/leaderboard
    @GetMapping("/leaderboard")
    public List<Player> getLeaderboard() {
        return repository.findTop10ByOrderByHighscoreDesc();
    }
}
