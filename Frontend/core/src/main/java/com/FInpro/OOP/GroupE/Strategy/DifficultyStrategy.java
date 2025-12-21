package com.FInpro.OOP.GroupE.Strategy;

import java.util.HashMap;


public interface DifficultyStrategy {

    // 1. Returns the probability weights for spawning obstacles
    HashMap<String, Float> getObstacleWeights();

    // 2. Returns how many seconds between each obstacle spawn
    float getSpawnInterval();

    // 3. Returns the distance required to finish the level
    float getLevelLength();

    // 4. Returns the display name of the difficulty
    String getDifficultyName();
}
