package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.AnimationManager;
import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Util.Animation;

public class Player extends GameObject {

    BoxBody body;
    AnimationManager animManager;

    public Player(Transform transform) {
        super(transform);
        setLayout(5);
        addComponent(new SpriteRenderer(this, Gdx.files.internal("badlogic.jpg")));
        body = new BoxBody(this, 100, 120, BodyDef.BodyType.DynamicBody);
        addComponent(body);
        animManager = new AnimationManager(this);
        addComponent(animManager);
        animManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("animation.png")), 6, 5, 0.025f, true), 0);
        animManager.setCurrentAnimation(0);
    }

    protected void update(float delta) {

        Game.debug(3, "Velx : " + body.getBody().getLinearVelocity().x);
        Game.debug(4, "Vely : " + body.getBody().getLinearVelocity().y);

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            body.getBody().setLinearVelocity(new Vector2(200, body.getBody().getLinearVelocity().y));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            body.getBody().setLinearVelocity(new Vector2(-200, body.getBody().getLinearVelocity().y));
        }

        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            body.getBody().setLinearVelocity(new Vector2(body.getBody().getLinearVelocity().x, 200));
        }

        Game.getCurrentScreen().camera.position.set(getTransform().getPosition().x , Gdx.graphics.getHeight() / 2, 0);

    }
}
