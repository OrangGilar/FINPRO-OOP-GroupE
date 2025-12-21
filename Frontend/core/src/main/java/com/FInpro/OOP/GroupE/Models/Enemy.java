package com.FInpro.OOP.GroupE.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Enemy {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;

    private final float GRAVITY = -600f;
    private final float MOVE_SPEED = 100f;

    private float width = 50f;
    private float height = 50f;

    private float changeDirectionTimer;
    private float currentDirection;

    private Rectangle bounds;

    public Enemy(float startX, float startY) {
        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.GREEN);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();

        this.position = new Vector2(startX, startY);
        this.velocity = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, width, height);

        pickRandomDirection();
    }

    private void pickRandomDirection() {
        int dir = MathUtils.random(-1, 1);
        currentDirection = (float) dir;
        changeDirectionTimer = MathUtils.random(0.5f, 2.0f);
    }

    public void update(float dt) {
        velocity.y += GRAVITY * dt;

        changeDirectionTimer -= dt;
        if (changeDirectionTimer <= 0) {
            pickRandomDirection();
        }

        velocity.x = currentDirection * MOVE_SPEED;
        position.add(velocity.x * dt, velocity.y * dt);
        bounds.setPosition(position.x, position.y);
    }

    public void handleCollision(float groundHeight) {
        if (position.y < groundHeight) {
            position.y = groundHeight;
            velocity.y = 0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public Rectangle getBounds() {
        return bounds;
    }
}
