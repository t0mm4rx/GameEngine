package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;

public class SpriteRenderer extends Component {

    private TextureRegion texture;
    private GameObject go;
    private float width, height;

    public SpriteRenderer (GameObject go, FileHandle texture) {
        this.texture = new TextureRegion(new Texture(texture));
        this.go = go;
        width = getTexture().getWidth();
        height = getTexture().getHeight();
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void render() {
        Game.batch.draw(texture,
                go.getTransform().getPosition().x - width / 2,
                go.getTransform().getPosition().y - height / 2,
                width / 2,
                height / 2,
                width,
                height,
                go.getTransform().getScale().x,
                go.getTransform().getScale().y,
                go.getTransform().getRotation());
    }

    public void renderInHUD() {
        Game.HUDbatch.draw(texture,
                go.getTransform().getPosition().x - width / 2,
                go.getTransform().getPosition().y - height / 2,
                width / 2,
                height / 2,
                width,
                height,
                go.getTransform().getScale().x,
                go.getTransform().getScale().y,
                go.getTransform().getRotation());
    }

    public Texture getTexture() {
        return texture.getTexture();
    }

    public void setSize(float w, float h) {
        width = w;
        height = h;
    }

    public void scaleWidth(float w) {
        width = w * Gdx.graphics.getWidth() / 100;
        height = (getTexture().getWidth() / getTexture().getHeight()) * w * Gdx.graphics.getHeight() / 100;
    }

    public void scaleHeight(float h) {
        height = h * Gdx.graphics.getHeight() / 100;
        width = (getTexture().getWidth() / getTexture().getHeight()) * h * Gdx.graphics.getWidth() / 100;
    }

    public void flip(boolean x, boolean y) {
        texture.flip(x, y);
    }

    public void update() {
        getTexture().setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Linear);
    }
}
