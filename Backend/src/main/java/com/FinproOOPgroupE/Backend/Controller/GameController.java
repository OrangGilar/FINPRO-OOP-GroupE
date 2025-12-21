package com.FinproOOPgroupE.Backend.Controller;

import com.FinproOOPgroupE.Backend.Service.PlayerService;
import com.FinproOOPgroupE.Backend.dto.PlayerRequest;
import com.FinproOOPgroupE.Backend.dto.ScoreRequest;
import com.FinproOOPgroupE.Backend.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class GameController {

    @Autowired
    private PlayerService playerService; // Use Service, NOT Repository directly

    // 1. REGISTER
    @PostMapping("/players")
    public Player createPlayer(@RequestBody PlayerRequest request) {
        return playerService.registerOrGetPlayer(request);
    }

    // 2. SUBMIT SCORE
    @PostMapping("/scores")
    public Player updateScore(@RequestBody ScoreRequest request) {
        return playerService.processScoreSubmission(request);
    }

    // 3. LEADERBOARD
    @GetMapping("/leaderboard")
    public List<Player> getLeaderboard() {
        return playerService.getLeaderboard();
    }

    // 4. GET ALL
    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        return playerService.getAllPlayers();
    }

    // 5. GET ONE
    @GetMapping("/players/{id}")
    public Player getPlayer(@PathVariable String id) {
        return playerService.getPlayerById(id);
    }
}

