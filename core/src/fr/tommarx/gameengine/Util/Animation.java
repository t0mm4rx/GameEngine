package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.tommarx.gameengine.Game.GameObject;

public class Animation {

    com.badlogic.gdx.graphics.g2d.Animation anim;
    GameObject go;
    boolean looping;
    private int id;

    public Animation (GameObject go, Texture texture, int cols, int rows, float speed, boolean looping, int id) {
        this.go = go;
        this.looping = looping;
        this.id = id;
        TextureRegion[][] tmp = TextureRegion.split(texture, texture.getWidth() / cols, texture.getHeight() / rows);
        TextureRegion[] frames = new TextureRegion[cols * rows];
        int i = 0;
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < cols; y++) {
                frames[i] = tmp[x][y];
                i++;
            }
        }
        anim = new com.badlogic.gdx.graphics.g2d.Animation(speed, frames);
    }

    public com.badlogic.gdx.graphics.g2d.Animation getAnimation() {
        return anim;
    }

    public int getId() {
        return id;
    }

    public boolean isLooping() {
        return looping;
    }
}
