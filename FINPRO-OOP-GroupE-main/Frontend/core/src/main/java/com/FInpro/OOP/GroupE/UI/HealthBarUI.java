package com.FInpro.OOP.GroupE.UI;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.FInpro.OOP.GroupE.Observer.HealthListener;

public class HealthBarUI implements HealthListener {
    private float x;
    private float y;
    private float width;
    private float height;
    private float currentpct; //currenct percentage from 4.0 to 1.0

    public HealthBarUI(float x, float y){
        this.x = x;
        this.y = y;
        this.width = 200;
        this.height = 20;
        this.currentpct = 4.0f;
    }

    @Override
    public void onhealthChanged(int currenthealth, int maxHealth){
        this.currentpct = (float) currenthealth / maxHealth;
        if (this.currentpct < 0){
            this.currentpct = 0;
        }
    }

    public void render(ShapeRenderer shapeRenderer){
        // rendering the healthbar ui
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.rect(x,y,width,height);
        // making the border
        shapeRenderer.setColor(Color.WHITE);
        shapeRenderer.rect(x,y,width,height);
    }
}
