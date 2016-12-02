package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class Parallax extends Screen{

    EmptyGameObject background, ground, houses;

    public Parallax(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        background = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        ground = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        houses = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, 64)));

        houses.addComponent(new SpriteRenderer(houses, Gdx.files.internal("level/houses1.png")));
        houses.setLayout(1);
        houses.setScrollingSpeed(0f);

        background.addComponent(new BoxRenderer(background, Gdx.graphics.getWidth() * 10, Gdx.graphics.getHeight() * 10, new Color(54f / 255, 131f / 255, 169f / 255, 1f)));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("level/cloud1.png"), -300, 100));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("level/cloud1.png"), -130, 130));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("level/cloud1.png"), 45, 90));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("level/cloud1.png"), 310, 120));
        background.setLayout(0);
        background.setScrollingSpeed(.5f);


        add(background);
        add(ground);
        add(houses);
    }

    public void update() {

        Game.debug(1, camera.position.x + "");

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(2, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-2, 0);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 2);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -2);
        }
    }

}
