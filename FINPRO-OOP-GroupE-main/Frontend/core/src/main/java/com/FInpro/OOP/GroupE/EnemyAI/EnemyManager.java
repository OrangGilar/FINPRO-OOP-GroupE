package com.FInpro.OOP.GroupE.EnemyAI;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.FInpro.OOP.GroupE.Models.Player;
import java.util.ArrayList;
import java.util.List;

public class EnemyManager {
    private List<Enemy> enemies = new ArrayList<>();

    public void addEnemy(Enemy enemy) {
        enemies.add(enemy);
    }

    public void update(float delta, Player player) {
        for (Enemy e : enemies) {
            e.update(delta, player);
        }
    }

    public void render(SpriteBatch batch) {
        for (Enemy e : enemies) {
            e.render(batch);
        }
    }

    public void dispose() {
        for (Enemy e : enemies) {
            e.dispose();
        }
    }
}
