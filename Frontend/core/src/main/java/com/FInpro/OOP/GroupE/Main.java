package com.FInpro.OOP.GroupE;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.FInpro.OOP.GroupE.Models.Bullet;
import com.FInpro.OOP.GroupE.Models.Enemy;
import com.FInpro.OOP.GroupE.Models.Player;
import com.FInpro.OOP.GroupE.command.InputHandler;

import java.util.Iterator;

public class Main extends ApplicationAdapter {
    private SpriteBatch batch;
    private Background background;
    private Ground ground;
    private OrthographicCamera camera;
    private Viewport viewport;

    private Player player;
    private Array<Enemy> enemies;

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

        enemies = new Array<>();
        enemies.add(new Enemy(400, 200));
        enemies.add(new Enemy(600, 200));
        enemies.add(new Enemy(800, 200));

        inputHandler = new InputHandler(player);
        Gdx.input.setInputProcessor(inputHandler);
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height, true);
    }

    @Override
    public void render() {
        float dt = Gdx.graphics.getDeltaTime();
        ScreenUtils.clear(0.4f, 0.6f, 0.9f, 1f);

        if (Gdx.input.isKeyPressed(Input.Keys.Z)) {
            player.shoot();
        }

        player.update(dt);
        player.handleCollision(ground.getHeight());

        for (Enemy enemy : enemies) {
            enemy.update(dt);
            enemy.handleCollision(ground.getHeight());
        }

        checkCollisions();

        camera.position.x = player.getX();
        camera.update();
        batch.setProjectionMatrix(camera.combined);
        background.update(camera.position.x);

        batch.begin();
        background.render(batch, viewport);
        ground.render(batch, viewport, camera.position.x);

        for (Enemy enemy : enemies) {
            enemy.render(batch);
        }

        player.render(batch);
        batch.end();
    }

    private void checkCollisions() {
        Array<Bullet> bullets = player.getBullets();
        Iterator<Enemy> enemyIter = enemies.iterator();

        while (enemyIter.hasNext()) {
            Enemy enemy = enemyIter.next();
            Iterator<Bullet> bulletIter = bullets.iterator();

            while (bulletIter.hasNext()) {
                Bullet bullet = bulletIter.next();
                if (bullet.getBounds().overlaps(enemy.getBounds())) {
                    bullet.dispose();
                    bulletIter.remove();
                    enemy.dispose();
                    enemyIter.remove();
                    break;
                }
            }
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        background.dispose();
        ground.dispose();
        player.dispose();
        for (Enemy enemy : enemies) {
            enemy.dispose();
        }
    }
}
