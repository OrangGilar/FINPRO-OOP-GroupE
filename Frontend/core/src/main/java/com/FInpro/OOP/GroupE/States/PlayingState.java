package com.FInpro.OOP.GroupE.States;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.FInpro.OOP.GroupE.Models.Player;
import com.frontend.entities.LevelGoal;
import com.nama.frontend.obstacles.ObstacleFactory;
import com.nama.frontend.obstacles.BaseObstacle;
import com.FInpro.OOP.GroupE.Strategy.DifficultyStrategy; // Import Interface
import com.FInpro.OOP.GroupE.UI.HealthBarUI;
import com.FInpro.OOP.GroupE.Manager.GameDataManager;
import com.FInpro.OOP.GroupE.Obstacle.CoinFactory;
import com.FInpro.OOP.GroupE.Models.Coin;

public class PlayingState extends GameState {
    private Player player;
    private ObstacleFactory obstacleFactory;
    private CoinFactory coinFactory;
    private DifficultyStrategy strategy; // Stores the chosen difficulty
    private HealthBarUI healthBar;
    private ShapeRenderer sr;
    private float spawnTimer;
    private float coinSpawnTimer;
    private LevelGoal goal;
    private boolean goalSpawned = false;
    private int coinsCollected = 0;

    // CONSTRUCTOR UPDATED: Accepts DifficultyStrategy
    public PlayingState(GameStateManager gsm, DifficultyStrategy selectedStrategy) {
        super(gsm);
        this.strategy = selectedStrategy; // Set the strategy chosen by user

        player = new Player(100, 100);

        obstacleFactory = new ObstacleFactory();
        obstacleFactory.setWeights(strategy.getObstacleWeights()); // Apply weights!

        coinFactory = new CoinFactory();

        sr = new ShapeRenderer();
        healthBar = new HealthBar(20, 430);
        player.addHealthListener(healthBar);
    }

    @Override
    public void update(float dt) {
        player.update(dt);
        if(Gdx.input.isKeyPressed(Input.Keys.W)) player.fly();

        // --- OBSTACLE SPAWNING (Based on Strategy Speed) ---
        spawnTimer += dt;
        if(spawnTimer > strategy.getSpawnInterval()) {
            obstacleFactory.createRandomObstacle(900);
            spawnTimer = 0;
        }
        obstacleFactory.updateAndRender(dt, null);

        // --- COIN SPAWNING ---
        coinSpawnTimer += dt;
        if(coinSpawnTimer > 3.0f) {
            coinFactory.createCoinGroup(900, (Math.random()>0.5?100:250));
            coinSpawnTimer=0;
        }
        coinFactory.updateAndRender(dt, null);

        checkCollisions();

        // --- GOAL LOGIC (Based on Strategy Length) ---
        if (!goalSpawned && player.getPosition().x > strategy.getLevelLength()) {
            goal = new LevelGoal(player.getPosition().x + 500, 100);
            goalSpawned = true;
        }
        if (goalSpawned) {
            goal.update(dt);
            if (player.getCollider().overlaps(goal.getCollider())) {
                completeLevel();
            }
        }

        // --- DEATH LOGIC ---
        if(player.isDead()) {
            int score = (int) player.getPosition().x;
            // Send data even on death? Usually yes for "Distance Travelled"
            GameDataManager.getInstance().saveScore(score, coinsCollected, score);

            // Go to Game Over State
            gsm.set(new GameOverState(gsm, score));
        }
    }

    private void checkCollisions() {
        // Obstacles
        for(BaseObstacle o : obstacleFactory.getActiveObstacles()) {
            if(o.isActive() && player.getCollider().overlaps(o.getCollider())) {
                if(o.getClass().getSimpleName().equals("FGrade")) player.decreaseGPA(0.5f);
                else player.decreaseGPA(0.1f);
                o.setActive(false);
            }
        }
        // Coins
        for(Coin c : coinFactory.getActiveCoins()) {
            if(c.isActive() && player.getCollider().overlaps(c.getCollider())) {
                coinsCollected++;
                coinFactory.removeCoin(c);
            }
        }
    }

    private void completeLevel() {
        int score = (int) player.getPosition().x * 10;
        GameDataManager.getInstance().saveScore(score, coinsCollected, (int)player.getPosition().x);
        gsm.set(new WinState(gsm, score));
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        player.render(sb);
        if(goalSpawned) goal.render(sb);
        obstacleFactory.updateAndRender(0, sb);
        coinFactory.updateAndRender(0, sb);
        healthBar.renderText(sb);
        sb.end();

        sr.begin(ShapeRenderer.ShapeType.Filled);
        healthBar.renderShapes(sr);
        sr.end();
    }

    @Override
    public void dispose() { player.dispose(); sr.dispose(); healthBar.dispose(); if(goal!=null) goal.dispose(); }
}
