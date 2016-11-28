package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.ConeLight;
import fr.tommarx.gameengine.Components.PointLight;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;


public class LightScreen extends Screen {
    EmptyGameObject sun;
    public LightScreen(Game game) {
        super(game);
    }

    public void show() {
        isLightsEnabled(true);
        rayHandler.setAmbientLight(1f, 1f, 1f, .003f);

        addLayout("Background", 0);

        EmptyGameObject background = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("background2.png")));
        background.getSpriteRenderer().scaleHeight(100);
        background.setLayout("Background");
        addGameObject(background);

        /*EmptyGameObject torch;
        torch = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), new Vector2(.4f, .4f), 0));
        torch.addComponent(new PointLight(torch, 1000, 100, Color.ORANGE, rayHandler));
        torch.addComponent(new SpriteRenderer(torch, Gdx.files.internal("torch.gif")));
        addGameObject(torch);*/

        sun = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), new Vector2(.4f, .4f), 0));
        sun.addComponent(new ConeLight(sun, 1000, 400, Color.ORANGE, rayHandler, 50));
        addGameObject(sun);

    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            sun.getTransform().setRotation(sun.getTransform().getRotation() - 2);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            sun.getTransform().setRotation(sun.getTransform().getRotation() + 2);
        }
    }

}
