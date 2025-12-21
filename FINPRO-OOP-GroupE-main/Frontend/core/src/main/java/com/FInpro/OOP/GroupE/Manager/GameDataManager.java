package com.FInpro.OOP.GroupE.Manager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.FInpro.OOP.GroupE.Service.BackendService;

public class GameDataManager {
    private static GameDataManager instance;
    private BackendService service;
    private Preferences prefs;

    //  key ID to connecting to the database!
    private String playerId;
    private String username;

    private GameDataManager() {
        service = new BackendService();
        prefs = Gdx.app.getPreferences("UniRunPrefs");

        // Load saved ID
        playerId = prefs.getString("playerId", null);
        username = prefs.getString("username", "Student");
    }

    public static GameDataManager getInstance() {
        if (instance == null) instance = new GameDataManager();
        return instance;
    }

    public boolean isRegistered() {
        return playerId != null;
    }

    // Call this from MenuState
    public void register(String name, final Runnable onSuccess) {
        service.createPlayer(name, new BackendService.RequestCallback() {
            @Override
            public void onSuccess(String response) {

                JsonValue json = new JsonReader().parse(response);

                playerId = json.getString("playerId");
                username = json.getString("username");

                // Save to local file
                prefs.putString("playerId", playerId);
                prefs.putString("username", username);
                prefs.flush();

                System.out.println("Registered ID: " + playerId);
                if (onSuccess != null) onSuccess.run();
            }

            @Override
            public void onError(String error) {
                System.err.println("Registration failed: " + error);
            }
        });
    }

    // Call this when Game Over
    public void saveScore(int score, int coins, int distance) {
        if (playerId == null) return; 

        service.submitScore(playerId, score, coins, distance, new BackendService.RequestCallback() {
            @Override
            public void onSuccess(String response) {
                System.out.println("Score saved to database!");
            }

            @Override
            public void onError(String error) {
                System.err.println("Failed to save score: " + error);
            }
        });
    }
}
