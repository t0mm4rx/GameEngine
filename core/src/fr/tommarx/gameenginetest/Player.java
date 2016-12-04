package fr.tommarx.gameenginetest;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.AnimationManager;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Util.Animation;

public class Player extends GameObject {

    CircleBody body;
    SpriteRenderer spriteRenderer;
    AnimationManager animationManager;
    private final float acceleration = 20, max_speed = 300, deceleration = 2;
    final int UP = 0, DOWN = 1, LEFT = 2, RIGHT = 3;
    private int lastDirection;

    public Player(Transform transform) {
        super(transform);
        //Game.debugging = true;

        lastDirection = DOWN;

        spriteRenderer = new SpriteRenderer(this, Gdx.files.internal("Player/down.png"));
        addComponent(spriteRenderer);

        animationManager = new AnimationManager(this);
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_up.png")), 9, 1, .1f, true, UP));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_right.png")), 9, 1, .1f, true, RIGHT));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_left.png")), 9, 1, .1f, true, LEFT));
        animationManager.addAnimation(new Animation(this, new Texture(Gdx.files.internal("Player/walk_down.png")), 9, 1, .1f, true, DOWN));
        animationManager.setCurrentAnimation(DOWN);
        addComponent(animationManager);

        body = new CircleBody(this, 20, BodyDef.BodyType.DynamicBody);
        addComponent(body);
    }

    protected void update(float delta) {

        Game.debug(1, "VelX = " + body.getBody().getLinearVelocity().x + " && VelY = " + body.getBody().getLinearVelocity().y);
        Game.debug(2, "Direction = " + getDirection());
        Game.debug(3, "IsMoving = " + isMoving());


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
        } else if (body.getBody().getLinearVelocity().x < 0) {
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x + deceleration, body.getBody().getLinearVelocity().y);
        }

        if (body.getBody().getLinearVelocity().x > -2 && body.getBody().getLinearVelocity().x < 2) {
            body.getBody().setLinearVelocity(0, body.getBody().getLinearVelocity().y);
        }

        if (body.getBody().getLinearVelocity().y > -2 && body.getBody().getLinearVelocity().y < 2) {
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, 0);
        }

        if (body.getBody().getLinearVelocity().y > 0) {
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y - deceleration);
        } else if (body.getBody().getLinearVelocity().y < 0) {
            body.getBody().setLinearVelocity(body.getBody().getLinearVelocity().x, body.getBody().getLinearVelocity().y + deceleration);
        }


        if (isMoving()) {
            if (animationManager.getCurrentAnimation() != getDirection()) {
                animationManager.setCurrentAnimation(getDirection());
            }
        } else {
            if (animationManager.getCurrentAnimation() != -1) {
                animationManager.setCurrentAnimation(-1);
            }
            if (getDirection() == DOWN) {
                getSpriteRenderer().setTexture(new TextureRegion(new Texture(Gdx.files.internal("Player/down.png"))));
            }
            if (getDirection() == UP) {
                getSpriteRenderer().setTexture(new TextureRegion(new Texture(Gdx.files.internal("Player/up.png"))));
            }
            if (getDirection() == RIGHT) {
                getSpriteRenderer().setTexture(new TextureRegion(new Texture(Gdx.files.internal("Player/right.png"))));
            }
            if (getDirection() == LEFT) {
                getSpriteRenderer().setTexture(new TextureRegion(new Texture(Gdx.files.internal("Player/left.png"))));
            }
        }

        getTransform().setRotation(0);
    }


    public int getDirection() {

        float speedY = body.getBody().getLinearVelocity().y;
        float speedX = body.getBody().getLinearVelocity().x;

        if (speedX == 0 && speedY == 0) {
            return lastDirection;
        }

        if (speedX < 0) {
            speedX = -speedX;
        }
        if (speedY < 0) {
            speedY = -speedY;
        }

        if (speedX > speedY) {
            if (body.getBody().getLinearVelocity().x < 0) {
                lastDirection = LEFT;
                return LEFT;
            }
            if (body.getBody().getLinearVelocity().x > 0) {
                lastDirection = RIGHT;
                return RIGHT;
            }
        }
        if (speedY > speedX) {
            if (body.getBody().getLinearVelocity().y < 0) {
                lastDirection = DOWN;
                return DOWN;
            }
            if (body.getBody().getLinearVelocity().y > 0) {
                lastDirection = UP;
                return UP;
            }
        }

        return 0;

    }

    public boolean isMoving() {
        float speedY = body.getBody().getLinearVelocity().y;
        float speedX = body.getBody().getLinearVelocity().x;

        if (speedX < 0) {
            speedX = -speedX;
        }
        if (speedY < 0) {
            speedY = -speedY;
        }

        if (speedX < 1 && speedY < 1) {
            return false;
        }
        return true;
    }
}
