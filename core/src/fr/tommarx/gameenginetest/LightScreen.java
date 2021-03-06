package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.ConeLight;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.UI.UICanvas;


public class LightScreen extends Screen {

    EmptyGameObject player, overlay;
    UICanvas ui;

    public LightScreen(Game game) {
        super(game);
    }

    public void show() {
        isLightsEnabled(true);
        rayHandler.setAmbientLight(1f, 1f, 1f, .01f);

        world.setGravity(new Vector2(0, 0));


        EmptyGameObject background = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        background.addComponent(new SpriteRenderer(background, Gdx.files.internal("background2.png")));
        background.getSpriteRenderer().scaleHeight(100);
        background.setLayout(0);
        add(background);

        EmptyGameObject sun;
        sun = new EmptyGameObject(new Transform(new Vector2(0, 0), new Vector2(.4f, .4f), -45));
        sun.addComponent(new ConeLight(sun, 1000, 600, Color.ORANGE, rayHandler, 30));
        sun.setTag("Light");
        sun.setLayout(1);
        add(sun);

        EmptyGameObject sun2;
        sun2 = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth(), 0), new Vector2(.4f, .4f), 45));
        sun2.addComponent(new ConeLight(sun2, 1000, 600, Color.WHITE, rayHandler, 30));
        sun2.setTag("Light");
        sun2.setLayout(1);
        add(sun2);

        player = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2), new Vector2(.2f, .2f), 0));
        player.addComponent(new BoxBody(player, 256, 256, BodyDef.BodyType.DynamicBody));
        player.addComponent(new SpriteRenderer(player, Gdx.files.internal("Badlogic.jpg")));
        player.setLayout(2);
        add(player);

        EmptyGameObject wall = new EmptyGameObject(new Transform(new Vector2(300, 300), new Vector2(.3f, .3f), 0));
        wall.addComponent(new SpriteRenderer(wall, Gdx.files.internal("Badlogic.jpg")));
        wall.addComponent(new BoxBody(wall, 256, 256, BodyDef.BodyType.StaticBody));
        wall.setLayout(0);
        add(wall);

        /*UICanvas ui = new UICanvas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, Gdx.files.internal("testskin/uiskin.json"));
        add(ui);*/

        overlay = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        overlay.addComponent(new BoxRenderer(overlay, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Color(0, 0, 0, 0)));
        overlay.setLayout(1);
        add(overlay);

        Game.tweenManager.goTween(new Tween("AlphaLights", Tween.LINEAR_EASE_NONE, 1f, -1f, 1f, 0f, false));

    }

    public void update() {
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x > -200) {
                ((BoxBody)player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x - 10f,
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y
                ));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x < 200) {
                ((BoxBody)player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x + 10f,
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y
                ));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y < 200) {
                ((BoxBody)player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x,
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y + 10f
                ));
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y > -200) {
                ((BoxBody)player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().x,
                        ((BoxBody)player.getComponentByClass("BoxBody")).getBody().getLinearVelocity().y - 10f
                ));
            }
        }

        ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, Game.tweenManager.getValue("AlphaLights")));

        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }

    }

}
