package com.FInpro.OOP.GroupE.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.FInpro.OOP.GroupE.Strategy.HardDifficulty;
import com.FInpro.OOP.GroupE.Strategy.EasyDifficulty;

public class LevelSelectState extends GameState {

    private BitmapFont font;

    public LevelSelectState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        font.getData().setScale(1.5f);
    }

    @Override
    public void update(float dt) {
        // Option 1: Freshman Year (Easy)
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_1)) {
            // Pass the "Easy" strategy to the playing state
            gsm.set(new PlayingState(gsm, new EasyDifficulty()));
        }

        // Option 2: Senior Year (Hard)
        if (Gdx.input.isKeyJustPressed(Input.Keys.NUM_2)) {
            // Pass the "Hard" strategy to the playing state
            gsm.set(new PlayingState(gsm, new HardDifficulty()));
        }

        // Back to Menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        font.setColor(Color.CYAN);
        font.draw(sb, "SELECT YOUR YEAR (LEVEL)", 250, 400);

        font.setColor(Color.WHITE);
        font.draw(sb, "[1] Freshman Year (Easy Mode)", 200, 300);
        font.draw(sb, "[2] Senior Year (Hard Mode)", 200, 250);

        font.setColor(Color.GRAY);
        font.draw(sb, "Press ESC to Return", 300, 100);

        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
