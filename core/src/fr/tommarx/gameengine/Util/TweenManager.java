package fr.tommarx.gameengine.Util;

import java.util.ArrayList;

public class TweenManager {

    ArrayList<Tween> tweens;

    public TweenManager () {
        tweens = new ArrayList<Tween>();
    }

    public void goTween(Tween tween) {
        tweens.add(tween);
    }

    public float getValue(String name) {
        ArrayList<Tween> tweensByName = new ArrayList<Tween>();
        for (Tween t : tweens) {
            if (t.getName().equals(name)) {
                tweensByName.add(t);
            }
        }

        for (Tween t : tweensByName) {
            if (!t.isFinished()) {
                return t.getValue();
            }
        }

        //System.err.println("No active tween found with the name : " + name);
        return 0;
    }

    public void update() {
        ArrayList<Tween> toDelete = new ArrayList<Tween>();
        for (Tween tween : tweens) {
            if (tween.isFinished()) {
                toDelete.add(tween);
            }
            tween.update();
        }
        tweens.removeAll(toDelete);
    }

}
