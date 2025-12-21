package com.FInpro.OOP.GroupE.command;

import com.FInpro.OOP.GroupE.Models.Player;

public class MoveCommand implements Command {
    private Player player;
    private float directionX;

    public MoveCommand(Player player, float directionX) {
        this.player = player;
        this.directionX = directionX;
    }

    @Override
    public void execute() {
        player.setVelocityX(directionX);
    }
}
