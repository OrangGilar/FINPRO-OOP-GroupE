package com.FInpro.OOP.GroupE.Models;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class Player {
    private Texture texture;
    private Vector2 position;
    private Vector2 velocity;

    private final float GRAVITY = -600f;
    private final float JUMP_FORCE = 400f;
    private final float MOVE_SPEED = 200f;
    private final int MAX_JUMPS = 2;

    private float width = 50f;
    private float height = 50f;
    private boolean onGround = false;
    private int jumpCount = 0;

    private boolean facingRight = true;
    private Array<Bullet> bullets;

    private long lastShotTime;
    private final long SHOOT_COOLDOWN = 200;

    private Rectangle bounds;

    public Player() {
        Pixmap pixmap = new Pixmap((int)width, (int)height, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.BLUE);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();

        this.position = new Vector2(100, 200);
        this.velocity = new Vector2(0, 0);
        this.bounds = new Rectangle(position.x, position.y, width, height);
        this.bullets = new Array<>();
        this.lastShotTime = 0;
    }

    public void update(float dt) {
        velocity.y += GRAVITY * dt;
        position.add(velocity.x * dt, velocity.y * dt);
        bounds.setPosition(position.x, position.y);

        if (velocity.x > 0) facingRight = true;
        if (velocity.x < 0) facingRight = false;

        Iterator<Bullet> iter = bullets.iterator();
        while (iter.hasNext()) {
            Bullet bullet = iter.next();
            bullet.update(dt);
            if (!bullet.isActive()) {
                bullet.dispose();
                iter.remove();
            }
        }
    }

    public void setVelocityX(float x) {
        this.velocity.x = x * MOVE_SPEED;
    }

    public void jump() {
        if (jumpCount < MAX_JUMPS) {
            velocity.y = JUMP_FORCE;
            onGround = false;
            jumpCount++;
        }
    }

    public void shoot() {
        if (TimeUtils.millis() - lastShotTime > SHOOT_COOLDOWN) {
            float bulletX = facingRight ? position.x + width : position.x - 10;
            float bulletY = position.y + (height / 2);
            float direction = facingRight ? 1 : -1;

            bullets.add(new Bullet(bulletX, bulletY, direction));
            lastShotTime = TimeUtils.millis();
        }
    }

    public void handleCollision(float groundHeight) {
        if (position.y < groundHeight) {
            position.y = groundHeight;
            velocity.y = 0;
            onGround = true;
            jumpCount = 0;
        }
        bounds.setPosition(position.x, position.y);
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, position.x, position.y, width, height);
        for (Bullet bullet : bullets) {
            bullet.render(batch);
        }
    }

    public void dispose() {
        texture.dispose();
        for (Bullet bullet : bullets) {
            bullet.dispose();
        }
    }

    public float getX() {
        return position.x;
    }

    public Array<Bullet> getBullets() {
        return bullets;
    }
}
