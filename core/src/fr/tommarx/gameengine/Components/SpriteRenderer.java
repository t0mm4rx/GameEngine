package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;

public class SpriteRenderer extends Component {

    private TextureRegion texture;
    private GameObject go;
    private float width;
    private float height;
    private float offsetX;
    private float offsetY;

    public SpriteRenderer (GameObject go, FileHandle texture) {
        this.texture = new TextureRegion(new Texture(texture));
        this.go = go;
        width = getTexture().getWidth();
        height = getTexture().getHeight();
        offsetX = 0;
        offsetY = 0;
    }

    public SpriteRenderer (GameObject go, FileHandle texture, float offsetX, float offsetY) {
        this.texture = new TextureRegion(new Texture(texture));
        this.go = go;
        width = getTexture().getWidth();
        height = getTexture().getHeight();
        this.offsetX = offsetX;
        this.offsetY = offsetY;
    }

    public void setOffset(float x, float y) {
        offsetX = x;
        offsetY = y;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setTexture(TextureRegion texture) {
        this.texture = texture;
    }

    public void render() {
        Game.batch.draw(texture,
                go.getTransform().getPosition().x - width / 2 + offsetX,
                go.getTransform().getPosition().y - height / 2 + offsetY,
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
                go.getTransform().getPosition().x - width / 2 + offsetX,
                go.getTransform().getPosition().y - height / 2 + offsetY,
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
