package com.FInpro.OOP.GroupE.command;

import com.FInpro.OOP.GroupE.Models.Player;

public class ShootCommand implements Command {
    private Player player;

    public ShootCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.shoot();
    }
}
