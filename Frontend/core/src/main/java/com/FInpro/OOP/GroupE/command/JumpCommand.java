package com.FInpro.OOP.GroupE.command;

import com.FInpro.OOP.GroupE.Models.Player;

public class JumpCommand implements Command {
    private Player player;

    public JumpCommand(Player player) {
        this.player = player;
    }

    @Override
    public void execute() {
        player.jump();
    }
}
