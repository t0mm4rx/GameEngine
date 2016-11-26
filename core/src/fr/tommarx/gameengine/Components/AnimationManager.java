package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;

import fr.tommarx.gameengine.Game.GameObject;

public class AnimationManager extends Component {

    GameObject go;
    ArrayList<fr.tommarx.gameengine.Util.Animation> anims;
    fr.tommarx.gameengine.Util.Animation currentAnimation;
    float stateTime;

    public AnimationManager(GameObject go) {
        this.go = go;
        anims = new ArrayList<fr.tommarx.gameengine.Util.Animation>();
        stateTime = 0f;
    }

    public void addAnimation(fr.tommarx.gameengine.Util.Animation anim, int id) {
        anims.add(id, anim);
    }

    public void setCurrentAnimation(int id) {
        stateTime = 0f;
        currentAnimation = anims.get(id);
    }

    public void render() {
        if (go.getSpriteRenderer() != null) {
            go.getSpriteRenderer().setTexture(currentAnimation.getAnimation().getKeyFrame(stateTime, currentAnimation.isLooping()));
        } else {
            System.err.println("Game object has no sprite renderer !");
        }
    }

    public void renderInHUD() {
        render();
    }

    public void update() {
        if (currentAnimation != null) {
            stateTime += Gdx.graphics.getDeltaTime();
        }
    }
}
