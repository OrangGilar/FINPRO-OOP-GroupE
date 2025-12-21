package com.FInpro.OOP.GroupE.Strategy;
import java.util.HashMap;

public class EasyDifficulty implements DifficultyStrategy {

    @Override
    public HashMap<String, Float> getObstacleWeights() {
        HashMap<String, Float> weights = new HashMap<>();
        // 80% Chance for Assignment (Ground - Easier to dodge)
        // 20% Chance for F-Grade (Air - Harder)
        weights.put("CACTUS", 0.8f);
        weights.put("BIRD", 0.2f);
        return weights;
    }

    @Override
    public float getSpawnInterval() {
        return 2.5f; // Slow spawn rate (Easier)
    }

    @Override
    public float getLevelLength() {
        return 2000f; // Shorter level
    }

    @Override
    public String getDifficultyName() {
        return "Freshman Year";
    }
}
