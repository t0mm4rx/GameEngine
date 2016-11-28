package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Game.GameObject;

public class PointLight extends Component {

    private GameObject go;
    private int power, length;
    private Color color;
    private box2dLight.PointLight light;
    private float lastX, lastY;
    private RayHandler rayHandler;

    public PointLight(GameObject go, int power, int length, Color color, RayHandler rayHandler) {
        this.go = go;
        this.power = power;
        this.length = length;
        this.color = color;
        this.rayHandler = rayHandler;
        light = new box2dLight.PointLight(rayHandler, power, color, length, go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;

    }

    public box2dLight.PointLight getLight() {
        return light;
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        if (go.getTransform().getPosition().x != lastX || go.getTransform().getPosition().y != lastY) {
            light.setPosition(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
            lastX = go.getTransform().getPosition().x;
            lastY = go.getTransform().getPosition().y;
        }
    }
}
