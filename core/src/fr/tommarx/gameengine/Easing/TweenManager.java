package fr.tommarx.gameengine.Easing;

import java.util.ArrayList;

public class TweenManager {

    ArrayList<Tween> tweens;
    ArrayList<Tween> deadTweens;

    public TweenManager () {
        tweens = new ArrayList<Tween>();
        deadTweens = new ArrayList<Tween>();
    }

    public void goTween(Tween tween) {
        tweens.add(tween);
    }

    public float getValue(String name) {
        for (Tween t : tweens) {
            if (t.getName().equals(name)) {
                return t.getValue();
            }
        }
        for (Tween t : deadTweens) {
            if (t.getName().equals(name)) {
                return t.getValue();
            }
        }

        //System.err.println("No tween found with the name : " + name);
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
        deadTweens.addAll(toDelete);
    }

}
