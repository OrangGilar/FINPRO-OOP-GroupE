package com.FInpro.OOP.GroupE.command;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.FInpro.OOP.GroupE.Models.Player;

public class InputHandler implements InputProcessor {
    private Command moveLeft;
    private Command moveRight;
    private Command stopMove;
    private Command jump;
    private Command shoot;

    public InputHandler(Player player) {
        moveLeft = new MoveCommand(player, -1);
        moveRight = new MoveCommand(player, 1);
        stopMove = new MoveCommand(player, 0);
        jump = new JumpCommand(player);
        shoot = new ShootCommand(player);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.LEFT) {
            moveLeft.execute();
            return true;
        }
        if (keycode == Input.Keys.RIGHT) {
            moveRight.execute();
            return true;
        }
        if (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP) {
            jump.execute();
            return true;
        }
        if (keycode == Input.Keys.Z) {
            shoot.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Input.Keys.LEFT || keycode == Input.Keys.RIGHT) {
            stopMove.execute();
            return true;
        }
        return false;
    }

    @Override public boolean keyTyped(char character) { return false; }
    @Override public boolean touchDown(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchUp(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchCancelled(int screenX, int screenY, int pointer, int button) { return false; }
    @Override public boolean touchDragged(int screenX, int screenY, int pointer) { return false; }
    @Override public boolean mouseMoved(int screenX, int screenY) { return false; }
    @Override public boolean scrolled(float amountX, float amountY) { return false; }
}
