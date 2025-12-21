package com.FInpro.OOP.GroupE.EnemyAI;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Enemy {
    private float x, y;
    private float speed;
    private Texture texture;
    private Rectangle hitbox;
    private EnemyAI ai;

    public Enemy(float x, float y, EnemyAI ai) {
        this.x = x;
        this.y = y;
        this.speed = 60f;
        this.ai = ai;
        this.texture = new Texture("enemy.png");
        this.hitbox = new Rectangle(x, y, 32, 32);
    }

    public void update(float delta, Player player) {
        ai.update(this, player, delta);
        hitbox.setPosition(x, y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, x, y);
    }

    public void dispose() {
        texture.dispose();
    }

    // Getters & setters
    public float getX() { return x; }
    public void setX(float x) { this.x = x; }
    public float getSpeed() { return speed; }
}
