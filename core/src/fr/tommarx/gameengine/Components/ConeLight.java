package fr.tommarx.gameengine.Components;


import com.badlogic.gdx.graphics.Color;

import box2dLight.RayHandler;
import fr.tommarx.gameengine.Game.GameObject;

public class ConeLight extends Component{
    private GameObject go;
    private int power, length;
    private Color color;
    private box2dLight.ConeLight light;
    private float lastX, lastY, lastAngle, angle;
    private RayHandler rayHandler;

    public ConeLight(GameObject go, int power, int length, Color color, RayHandler rayHandler, float angle) {
        this.go = go;
        this.power = power;
        this.length = length;
        this.color = color;
        this.angle = angle;
        this.rayHandler = rayHandler;
        light = new box2dLight.ConeLight(rayHandler, power, color, length, go.getTransform().getPosition().x, go.getTransform().getPosition().y, go.getTransform().getRotation() + 90, angle );
        lastX = go.getTransform().getPosition().x;
        lastY = go.getTransform().getPosition().y;
        lastAngle = go.getTransform().getRotation();
    }

    public box2dLight.ConeLight getLight() {
        return light;
    }

    public void setAngle(float angle) {
        light.setConeDegree(angle);
    }

    public float getAngle() {
        return light.getConeDegree();
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        if (go.getTransform().getPosition().x != lastX || go.getTransform().getPosition().y != lastY || go.getTransform().getRotation() != lastAngle) {
            light.setPosition(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
            light.setDirection(go.getTransform().getRotation());
            lastX = go.getTransform().getPosition().x;
            lastY = go.getTransform().getPosition().y;
            lastAngle = go.getTransform().getRotation();
        }
    }
}
