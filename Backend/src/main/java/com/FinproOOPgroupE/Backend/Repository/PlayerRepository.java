package com.FinproOOPgroupE.Backend.Repository;

import com.FinproOOPgroupE.Backend.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface PlayerRepository extends JpaRepository<Player, String> {

    // Find a player by username
    Player findByUsername(String username);

    // Get Top 10 High Scores (For the Leaderboard)
    List<Player> findTop10ByOrderByHighscoreDesc();
}
