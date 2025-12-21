package com.FInpro.OOP.GroupE.Strategy;

import java.util.HashMap;

public class HardDifficulty implements DifficultyStrategy {

    @Override
    public HashMap<String, Float> getObstacleWeights() {
        HashMap<String, Float> weights = new HashMap<>();
        // 30% Chance for Assignment
        // 70% Chance for F-Grade (Chaotic!)
        weights.put("CACTUS", 0.3f);
        weights.put("BIRD", 0.7f);
        return weights;
    }

    @Override
    public float getSpawnInterval() {
        return 1.0f; // Fast spawn rate (Harder)
    }

    @Override
    public float getLevelLength() {
        return 5000f; // Long level
    }

    @Override
    public String getDifficultyName() {
        return "Senior Year";
    }
}
