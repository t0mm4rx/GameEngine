package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Interpolation;

import java.lang.*;

public class Tween {

    private float duration, time, delay, value, from, to;
    private double timeB;
    private Interpolation interpolation;

    public Tween(Interpolation interpolation, float from, float to, float duration, float delay) {
        this.interpolation = interpolation;
        this.duration = duration;
        this.delay = delay;
        this.from = from;
        this.to = to;
        value = from;
        timeB = System.currentTimeMillis();
    }

    public float getValue() {
        return value;
    }

    public void update() {
        if (System.currentTimeMillis() - timeB >= delay * 1000) {
            time += Gdx.graphics.getDeltaTime();
            float progress = java.lang.Math.min(to, time / duration);
            value = interpolation.apply(progress);
        }
    }

}
