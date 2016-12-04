package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class TestScreen extends Screen
{

    //On créer un game object player
    EmptyGameObject player;
    EmptyGameObject ground;
    EmptyGameObject text;

    public TestScreen(Game game) {
        super(game);
    }

    public void update() {

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            ((BoxBody) player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(200, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            ((BoxBody) player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(-200, 0));
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            ((BoxBody) player.getComponentByClass("BoxBody")).getBody().setLinearVelocity(new Vector2(0, 200));
        }

    }

    public void show() {

        //Game.debugging = true;

        //On l'instancie
        player = new EmptyGameObject(new Transform(new Vector2(300, 300)));
        //On lui ajoute un component qui va aficher un carré blanc
        player.addComponent(new BoxRenderer(player, 50, 50, Color.WHITE));
        //On ajoute le player a la screen
        player.addComponent(new BoxBody(player, 50, 50, BodyDef.BodyType.DynamicBody));
        player.setLayout(1);
        add(player);

        ground = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, 70)));
        ground.addComponent(new BoxRenderer(ground, Gdx.graphics.getWidth(), 50, Color.CYAN));
        ground.addComponent(new BoxBody(ground, Gdx.graphics.getWidth(), 50, BodyDef.BodyType.StaticBody));
        ground.setLayout(1);
        add(ground);

        text = new EmptyGameObject(new Transform(new Vector2(300, 300)));
        text.addComponent(new Text(text, "Mon super jeu de ouf !!", Color.WHITE));
        text.setLayout(0);
        add(text);
    }
}
