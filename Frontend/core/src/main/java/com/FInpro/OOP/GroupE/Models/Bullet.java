package com.FInpro.OOP.GroupE.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Bullet {
    private Vector2 position;
    private float speed = 600f; // Increased speed slightly
    private float width = 10f;
    private float height = 5f;
    private float direction;
    private Texture texture;
    private boolean active;
    private Rectangle bounds;

    private final float MAX_DISTANCE = 50000f;

    public Bullet(float x, float y, float direction) {
        this.position = new Vector2(x, y);
        this.direction = direction;
        this.active = true;
        this.bounds = new Rectangle(x, y, width, height);

        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.YELLOW);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    public void update(float dt) {
        position.x += speed * direction * dt;
        bounds.setPosition(position.x, position.y);

        if (position.x < -MAX_DISTANCE || position.x > MAX_DISTANCE) {
            active = false;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public boolean isActive() {
        return active;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void dispose() {
        texture.dispose();
    }
}
