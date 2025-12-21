package com.FInpro.OOP.GroupE;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Ground {
    private Texture texture;
    private float height;

    public Ground() {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        texture = new Texture(pixmap);
        pixmap.dispose();
        height = 50f;
    }

    public void render(SpriteBatch batch, Viewport viewport, float cameraX) {
        float worldWidth = viewport.getWorldWidth();
        float leftEdge = cameraX - (worldWidth / 2);

        batch.draw(texture, leftEdge, 0, worldWidth, height);
    }

    public void dispose() {
        texture.dispose();
    }

    public float getHeight() {
        return height;
    }
}
