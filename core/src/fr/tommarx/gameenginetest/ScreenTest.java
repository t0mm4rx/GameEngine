package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.math.Vector2;

import box2dLight.PointLight;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class ScreenTest extends Screen {

    public ScreenTest(Game game) {
        super(game);
    }

    public void show() {

            add(new Player(new Transform(new Vector2(300,300), new Vector2(0.2f, 0.2f), 0)));
            add(new Wall(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, 50)), 500, 10));
            EmptyGameObject go = new EmptyGameObject(new Transform(new Vector2(300, 300), new Vector2(2, 2), 0));
            go.addComponent(new Text(go, Gdx.files.internal("font.ttf"), 40, "Test !!", Color.WHITE));
            go.setLayout(1);
            add(go);
            EmptyGameObject background = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
            background.addComponent(new SpriteRenderer(background, Gdx.files.internal("background.jpg")));
            background.getSpriteRenderer().scaleWidth(100);
            background.setLayout(0);
            //background.getSpriteRenderer().flip(true, true);
            add(background);

            EmptyGameObject hudTest = new EmptyGameObject(new Transform(new Vector2(20, 20)));
            hudTest.addComponent(new BoxRenderer(hudTest, 20, 20, Color.BLUE));
            addInHUD(hudTest);
        
    }

        public void update() {
            Game.debug(1, "DEBUG MODE");
            Game.debug(2, "FPS : " + Gdx.graphics.getFramesPerSecond());
            if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
                Game.debugging = !Game.debugging;
            }
            if (Gdx.input.isKeyJustPressed(Input.Keys.L)) {
                isLightsEnabled(true);
                rayHandler.setAmbientLight(.7f);
                new PointLight(rayHandler, 500, Color.BLUE, 1000, Gdx.graphics.getWidth() - 10, Gdx.graphics.getHeight() - 10);
            }
        }

}
