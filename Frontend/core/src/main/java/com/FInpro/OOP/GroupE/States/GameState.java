package com.FInpro.OOP.GroupE.States;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;

public abstract class GameState {

    protected OrthographicCamera camera;
    protected Vector3 mouse;
    protected GameStateManager gsm;

    protected GameState(GameStateManager gsm){
        this.gsm = gsm;
        camera = new OrthographicCamera();
        mouse = new Vector3();
        // setup default camera view
        camera.setToOrtho(false, 800, 480);
    }
    public abstract void update(float dt);
    public abstract void render(SpriteBatch sb);
    public abstract void dispose();
}
