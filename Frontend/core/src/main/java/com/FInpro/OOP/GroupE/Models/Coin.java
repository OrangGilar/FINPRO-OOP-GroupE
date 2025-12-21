package com.FInpro.OOP.GroupE.Models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Pool;

public class Coin {
    private Vector2 position;
    private Rectangle collider;
    private boolean active;

    private static Texture texture;

    public Coin(){
        this.position = new Vector2();
        this.collider = new Rectangle();
        this.active = false;
    }
    public void init(float x, float y){
        this.position.set(x, y);
        this.active = true;
        this.collider.set(x, y, texture.getWidth(), texture.getHeight());
    }
    public void update(float delta){
        collider.setPosition(position.x, position.y);
    }
    public void render(SpriteBatch batch){
        if (active){
            batch.draw(texture, position.x, position.y);
        }
    }
    public void reset(){
        this.position.set(0, 0);
        this.active = false;
    }
    public boolean isActive(){
        return active;
    }
    public void setActive(boolean active){
        this.active = active;
    }
    public Rectangle getCollider(){
        return collider;
    }
    public Vector2 getPosition(){
        return position;
    }
}
