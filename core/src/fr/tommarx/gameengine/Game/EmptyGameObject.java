package fr.tommarx.gameengine.Game;

import fr.tommarx.gameengine.Components.Transform;

public class EmptyGameObject extends GameObject {


    public EmptyGameObject(Transform transform) {
        super(transform);
    }

    protected void update(float delta) {

    }
}
