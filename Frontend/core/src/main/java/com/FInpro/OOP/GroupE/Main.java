package com.FInpro.OOP.GroupE;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.FInpro.OOP.GroupE.Models.Player; // Added Import
import com.FInpro.OOP.GroupE.command.InputHandler;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Ground ground;
    private OrthographicCamera camera;
    private Viewport viewport;
    private Player player;
    private InputHandler inputHandler;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new ExtendViewport(800, 480, camera);
        viewport.apply();

        background = new Background();
        ground = new Ground();
        player = new Player();

        inputHandler = new InputHandler(player);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        ScreenUtils.clear(0.4f, 0.6f, 0.9f, 1f);

        player.handleCollision(ground.getHeight());

        camera.position.x = player.getX();
        camera.update();
        batch.setProjectionMatrix(camera.combined);

        background.update(camera.position.x);

        batch.begin();
        background.render(batch, viewport);
        ground.render(batch, viewport, camera.position.x);
        player.render(batch);
        batch.end();
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        ground.dispose();
        player.dispose();
    }
}
