package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.math.Interpolation;

import java.util.ArrayList;

public class TweenManager {

    ArrayList<Tween> tweens;

    public TweenManager () {
        tweens = new ArrayList<Tween>();
    }

    public void goTween(Tween tween, int id) {
        tweens.add(id, tween);
    }

    public float getValue(int id) {
        return tweens.get(id).getValue();
    }

    public void update() {
        for (Tween tween : tweens) {
            tween.update();
        }
    }

}
