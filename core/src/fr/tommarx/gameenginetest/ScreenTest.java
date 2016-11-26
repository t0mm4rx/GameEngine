package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class ScreenTest extends Screen {

        public void show() {
            Game.addLayout("Background", 0);
            Game.addLayout("Foreground", 1);

            addGameObject(new Player(new Transform(new Vector2(300,300), new Vector2(0.2f, 0.2f), 0)));
            addGameObject(new Wall(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, 50)), 500, 10));
            EmptyGameObject go = new EmptyGameObject(new Transform(new Vector2(300, 300), new Vector2(2, 2), 0));
            go.addComponent(new Text(go, Gdx.files.internal("font.ttf"), 40, "Test !!", Color.WHITE));
            go.setLayout("Foreground");
            addGameObject(go);
            EmptyGameObject background = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
            background.addComponent(new SpriteRenderer(background, Gdx.files.internal("background.jpg")));
            background.getSpriteRenderer().scaleWidth(100);
            background.setLayout("Background");
            //background.getSpriteRenderer().flip(true, true);
            addGameObject(background);

            EmptyGameObject hudTest = new EmptyGameObject(new Transform(new Vector2(20, 20)));
            hudTest.addComponent(new BoxRenderer(hudTest, 20, 20, Color.BLUE));
            addGameObjectInHUD(hudTest);
        }

        public void update() {
            Game.debug(1, "DEBUG MODE");
            Game.debug(2, "FPS : " + Gdx.graphics.getFramesPerSecond());
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                Game.debugging = !Game.debugging;
            }
        }

}
