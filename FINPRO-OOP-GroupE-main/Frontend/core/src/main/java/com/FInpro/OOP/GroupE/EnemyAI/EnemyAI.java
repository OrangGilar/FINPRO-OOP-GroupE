package com.FInpro.OOP.GroupE.EnemyAI;

import com.FInpro.OOP.GroupE.Models.Player;

public interface EnemyAI {
    void update(Enemy enemy, Player player, float delta);
}
