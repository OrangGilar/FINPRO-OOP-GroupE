package com.FInpro.OOP.GroupE.EnemyAI;

import com.FInpro.OOP.GroupE.Models.Player;

public class PatrolAI implements EnemyAI {
    private float leftBound;
    private float rightBound;
    private int direction = -1;

    public PatrolAI(float leftBound, float rightBound) {
        this.leftBound = leftBound;
        this.rightBound = rightBound;
    }

    @Override
    public void update(Enemy enemy, Player player, float delta) {
        enemy.setX(enemy.getX() + direction * enemy.getSpeed() * delta);

        if (enemy.getX() <= leftBound || enemy.getX() >= rightBound) {
            direction *= -1;
        }
    }
}
