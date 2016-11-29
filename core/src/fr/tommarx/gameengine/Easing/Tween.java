package fr.tommarx.gameengine.Easing;

import com.badlogic.gdx.Gdx;

public class Tween {

    public static final int LINEAR_EASE_OUT = 0;
    public static final int LINEAR_EASE_IN = 1;
    public static final int LINEAR_EASE_NONE = 2;
    public static final int LINEAR_EASE_INOUT = 3;
    public static final int CUBE_EASE_OUT = 4;
    public static final int CUBE_EASE_IN = 5;
    public static final int CUBE_EASE_INOUT = 6;
    public static final int BOUNCE_EASE_OUT = 7;
    public static final int BOUNCE_EASE_IN = 8;
    public static final int BOUNCE_EASE_INOUT = 9;

    private float duration, time, delay, value, from, change;
    private int easingType;
    private double timeB;
    private boolean isFinished, repeat;
    private String name;

    public Tween(String name, int easingType, float from, float change, float duration, float delay, boolean repeat) {
        this.name = name;
        this.duration = duration;
        this.delay = delay;
        this.from = from;
        this.change = change;
        this.easingType = easingType;
        value = from;
        timeB = System.currentTimeMillis();
        isFinished = false;
        this.repeat = repeat;
    }

    public String getName() {
        return name;
    }

    public float getValue() {
        return value;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void update() {
        if (System.currentTimeMillis() - timeB > delay * 1000) {
            time += Gdx.graphics.getDeltaTime();
            switch (easingType) {
                case 0:
                    value = Linear.easeOut(time, from, change, duration);
                    break;
                case 1:
                    value = Linear.easeIn(time, from, change, duration);
                    break;
                case 2:
                    value = Linear.easeNone(time, from, change, duration);
                    break;
                case 3:
                    value = Linear.easeInOut(time, from, change, duration);
                    break;
                case 4:
                    value = Cubic.easeOut(time, from, change, duration);
                    break;
                case 5:
                    value = Cubic.easeIn(time, from, change, duration);
                    break;
                case 6:
                    value = Cubic.easeInOut(time, from, change, duration);
                    break;
                case 7:
                    value = Bounce.easeOut(time, from, change, duration);
                    break;
                case 8:
                    value = Bounce.easeIn(time, from, change, duration);
                    break;
                case 9:
                    value = Bounce.easeInOut(time, from, change, duration);
                    break;
            }
        }

        if (time >= duration) {

            if (value > .9f) {
                value = 1;
            } else {
                value = 0;
            }

            if (!repeat) {
                isFinished = true;
            } else {
                time = 0;
                if (value > .9f) {
                    from = 1f;
                } else {
                    from = 0f;
                }
                change = -change;
            }
        }
    }

}
