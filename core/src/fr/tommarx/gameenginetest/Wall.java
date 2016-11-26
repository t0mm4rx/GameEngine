package fr.tommarx.gameenginetest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.physics.box2d.BodyDef;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.GameObject;

public class Wall extends GameObject {

    public Wall(Transform transform, float width, float height) {
        super(transform);
        addComponent(new BoxRenderer(this, width, height, Color.BLUE));
        addComponent(new BoxBody(this, width, height, BodyDef.BodyType.StaticBody));
    }

    protected void update(float delta) {

    }
}
