package com.FInpro.OOP.GroupE.States;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.FInpro.OOP.GroupE.Manager.GameDataManager;

public class MenuState extends GameState {
    private BitmapFont font;
    private String statusMsg = "Press ENTER to Select Level";

    public MenuState(GameStateManager gsm) {
        super(gsm);
        font = new BitmapFont();
        font.getData().setScale(2);

        if (!GameDataManager.getInstance().isRegistered()) {
            Gdx.input.getTextInput(new Input.TextInputListener() {
                public void input(String text) {
                    GameDataManager.getInstance().register(text, null);
                    statusMsg = "Welcome, " + text + "!";
                }
                public void canceled() {}
            }, "Name", "", "Student Name");
        }
    }

    public void update(float dt) {
        // CHANGED: Go to Level Select instead of Playing directly
        if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)) {
            gsm.set(new LevelSelectState(gsm));
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.L)) {
            gsm.set(new LeaderboardState(gsm));
        }
    }

    public void render(SpriteBatch sb) {
        sb.begin();
        font.draw(sb, "Engineer Student", 300, 300);
        font.draw(sb, statusMsg, 200, 200);
        font.draw(sb, "[L] Leaderboard", 300, 150);
        sb.end();
    }
    public void dispose() { font.dispose(); }
}
