package com.FInpro.OOP.GroupE.Obstacle;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Pool;
import java.util.Random;
import com.FInpro.OOP.GroupE.Models.Coin;

public class CoinFactory {
    private final Pool<Coin> coinPool = new Pool<Coin>() {
        @Override
        protected Coin newObject() {
            return new Coin();
        }
    };
    private final Array<Coin> activeCoins = new Array<>();
    private Random rand = new Random();

    public void createCoin(float x, float y){
        Coin c = coinPool.obtain();
        c.init(x, y); // initialize
        activeCoins.add(c); // add to active list
    }
    //spawns of coin
    public void createCoinGroup(float startx, float starty){
        for (int i = 0; i < 3; i++){
            createCoin(startx + (i * 50), starty); // 50 pixel apart
        }
    }
    public void updateAndRender(float delta, SpriteBatch batch){
        for (int i = activeCoins.size - 1; i >= 0; i--){
            Coin c = activeCoins.get(i);
            c.update(delta);

            if (batch != null){
                c.render(batch);
            }
            if (!c.isActive() || c.getPosition().x < -100){
                removeCoin(c);
            }
        }
    }
    public void removeCoin(Coin c){
        activeCoins.removeValue(c, true);
        // free to pool recycling
        coinPool.free(c);
    }
    public Array<Coin> getActiveCoins(){
        return activeCoins;
    }
}
