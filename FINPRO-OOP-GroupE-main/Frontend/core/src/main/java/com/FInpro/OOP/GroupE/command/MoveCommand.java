package com.FInpro.OOP.GroupE.command;

import com.FInpro.OOP.GroupE.Models.Player;
import com.badlogic.gdx.Gdx;

public class MoveCommand implements Command {
    private Player player;
    private float directionX;
    private float directionY;

    public MoveCommand(Player player, float directionX, float directionY) {
        this.player = player;
        this.directionX = directionX;
        this.directionY = directionY;
    }

    @Override
    public void execute() {
        float dt = Gdx.graphics.getDeltaTime();
        player.move(directionX * dt, directionY * dt);
    }
}
