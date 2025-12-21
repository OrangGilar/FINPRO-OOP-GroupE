package com.FInpro.OOP.GroupE.command;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.FInpro.OOP.GroupE.Models.Player;

public class InputHandler implements InputProcessor {
    private Command moveUp;
    private Command moveDown;
    private Command moveLeft;
    private Command moveRight;

    public InputHandler(Player player) {
        moveUp = new MoveCommand(player, 0, 1);
        moveDown = new MoveCommand(player, 0, -1);
        moveLeft = new MoveCommand(player, -1, 0);
        moveRight = new MoveCommand(player, 1, 0);
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.UP) {
            moveUp.execute();
            return true;
        }
        if (keycode == Input.Keys.DOWN) {
            moveDown.execute();
            return true;
        }
        if (keycode == Input.Keys.LEFT) {
            moveLeft.execute();
            return true;
        }
        if (keycode == Input.Keys.RIGHT) {
            moveRight.execute();
            return true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
}
