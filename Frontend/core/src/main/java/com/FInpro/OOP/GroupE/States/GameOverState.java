package com.FInpro.OOP.GroupE.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameOverState extends GameState {

    private BitmapFont font;
    private int finalScore;

    public GameOverState(GameStateManager gsm, int score) {
        super(gsm);
        this.finalScore = score;
        font = new BitmapFont();
        font.getData().setScale(2);
    }

    @Override
    public void update(float dt) {
        // Press SPACE to Try Again (Goes back to Level Select)
        if (Gdx.input.isKeyJustPressed(Input.Keys.SPACE)) {
            gsm.set(new LevelSelectState(gsm));
        }

        // Press ESC to Main Menu
        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            gsm.set(new MenuState(gsm));
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();

        font.setColor(Color.RED);
        font.draw(sb, "ACADEMIC DROPOUT!", 250, 350);

        font.setColor(Color.WHITE);
        font.getData().setScale(1.5f);
        font.draw(sb, "Final Distance: " + finalScore + "m", 300, 280);

        font.setColor(Color.GRAY);
        font.getData().setScale(1.2f);
        font.draw(sb, "Press SPACE to Try Again", 280, 200);
        font.draw(sb, "Press ESC for Main Menu", 285, 150);

        sb.end();
    }

    @Override
    public void dispose() {
        font.dispose();
    }
}
