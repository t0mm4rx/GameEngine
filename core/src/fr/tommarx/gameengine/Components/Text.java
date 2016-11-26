package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;

public class Text extends Component {

    String text;
    BitmapFont font;
    GameObject go;
    GlyphLayout glyphLayout;
    Color color;

    public Text(GameObject go, String text, Color color) {
        this.go = go;
        this.text = text;
        font = new BitmapFont();
        glyphLayout = new GlyphLayout();
        this.color = color;
    }

    public Text(GameObject go, FileHandle fontFile, int size, String text, Color color) {
        this.go = go;
        this.text = text;
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(fontFile);
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = size;
        parameter.minFilter = Texture.TextureFilter.Linear;
        parameter.magFilter = Texture.TextureFilter.Linear;
        font = fontGenerator.generateFont(parameter);
        glyphLayout = new GlyphLayout();
        this.color = color;
    }

    public void render() {
        glyphLayout.setText(font, text);
        font.getData().setScale(go.getTransform().getScale().x, go.getTransform().getScale().y);
        font.setColor(color);
        font.draw(Game.batch,
                text,
                go.getTransform().getPosition().x - glyphLayout.width / 2,
                go.getTransform().getPosition().y - glyphLayout.height / 2
        );
    }

    public void renderInHUD() {
        glyphLayout.setText(font, text);
        font.getData().setScale(go.getTransform().getScale().x, go.getTransform().getScale().y);
        font.setColor(color);
        font.draw(Game.HUDbatch,
                text,
                go.getTransform().getPosition().x - glyphLayout.width / 2,
                go.getTransform().getPosition().y - glyphLayout.height / 2
        );
    }

    public Color getColor () {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void update() {
    }
}
