package com.FInpro.OOP.GroupE.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class Player {
    private Texture texture;
    private Vector2 position;
    private float speed = 150f;
    private float width = 50f;
    private float height = 50f;

    public Player() {
        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.RED);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();

        this.position = new Vector2(100, 100);
    }

    public void move(float deltaX, float deltaY) {
        position.x += deltaX * speed;
        position.y += deltaY * speed;
    }

    public void handleCollision(float groundHeight) {
        if (position.y < groundHeight) {
            position.y = groundHeight;
        }
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getX() {
        return position.x;
    }
}

