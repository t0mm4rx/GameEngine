package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.AnimationManager;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Util.Animation;

public class Player extends GameObject {

    CircleBody body;
    SpriteRenderer spriteRenderer;
    AnimationManager animationManager;
    private final float acceleration = 20, max_speed = 300, deceleration = 2;
    final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    int direction;

    public Player(Transform transform) {
        super(transform);
        direction = DOWN;
        spriteRenderer = new SpriteRenderer(this, Gdx.files.internal("Player/down.png"));
        addComponent(spriteRenderer);

        animationManager = new AnimationManager(this);
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_up.png")), 9, 1, .1f, true, UP));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_right.png")), 9, 1, .1f, true, RIGHT));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_left.png")), 9, 1, .1f, true, LEFT));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_down.png")), 9, 1, .1f, true, DOWN));
        addComponent(animationManager);

        body = new CircleBody(this, 20, BodyDef.BodyType.DynamicBody);
        addComponent(body);
    }

    protected void update(float delta) {

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            if (body.getBody().getLinearVelocity().x < max_speed) {
                body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x - acceleration, body.getBody().getLinearVelocity().y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            if (body.getBody().getLinearVelocity().x > -max_speed) {
                body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x + acceleration, body.getBody().getLinearVelocity().y);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            if (body.getBody().getLinearVelocity().y < max_speed) {
                body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y - acceleration);
            }
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            if (body.getBody().getLinearVelocity().y > -max_speed) {
                body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y + acceleration);
            }
        }

        if (body.getBody().getLinearVelocity().x > 0) {
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x - deceleration, body.getBody().getLinearVelocity().y);
            direction = RIGHT;
        } else if (body.getBody().getLinearVelocity().x < 0) {
            direction = LEFT;
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x + deceleration, body.getBody().getLinearVelocity().y);
        }

        if (body.getBody().getLinearVelocity().y > 0) {
            direction = UP;
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y - deceleration);
        } else if (body.getBody().getLinearVelocity().y < 0) {
            direction = DOWN;
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y + deceleration);
        }

        if (animationManager.getCurrentAnimation() != direction) {
            animationManager.setCurrentAnimation(direction);
        }

        getTransform().setRotation(0);
    }
}
