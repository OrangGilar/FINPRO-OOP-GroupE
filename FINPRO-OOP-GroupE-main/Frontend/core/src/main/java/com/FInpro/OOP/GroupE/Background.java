package com.FInpro.OOP.GroupE;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.viewport.Viewport;

public class Background {
    private Texture backgroundTexture;
    private TextureRegion backgroundRegion;
    private float imageWidth;
    private float imageHeight;
    private float cameraX;
    private float parallaxSpeed = 0.5f;

    public Background() {
        backgroundTexture = new Texture(Gdx.files.internal("background.jpeg"));
        backgroundTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        backgroundRegion = new TextureRegion(backgroundTexture);
        imageWidth = backgroundTexture.getWidth();
        imageHeight = backgroundTexture.getHeight();
    }

    public void update(float cameraX) {
        this.cameraX = cameraX;
    }

    public void render(SpriteBatch batch, Viewport viewport) {
        float worldWidth = viewport.getWorldWidth();
        float worldHeight = viewport.getWorldHeight();
        float scale = worldHeight / imageHeight;
        float scaledWidth = imageWidth * scale;
        float scaledHeight = imageHeight * scale;

        float absoluteMove = cameraX * parallaxSpeed;
        float leftEdge = cameraX - (worldWidth / 2);
        float offset = absoluteMove % scaledWidth;
        float startX = leftEdge - offset;

        if (startX > leftEdge) startX -= scaledWidth;

        for (float x = startX; x < leftEdge + worldWidth + scaledWidth; x += scaledWidth) {
            batch.draw(backgroundRegion, x, 0, scaledWidth, scaledHeight);
        }
    }

    public void dispose() {
        backgroundTexture.dispose();
    }
}
