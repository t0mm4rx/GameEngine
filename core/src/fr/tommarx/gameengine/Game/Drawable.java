package fr.tommarx.gameengine.Game;


public abstract class Drawable {

    private int z = 0;
    private float scrollingSpeed = 0;
    protected boolean isGameObject = false;

    public abstract void render();
    public abstract void renderInHUD();
    public abstract void update();

    public boolean isGameObject() {
        return isGameObject;
    }

    public void setLayout(int z) {
        this.z = z;
    }

    public int getLayout() {
        return z;
    }

    public float getScrollingSpeed() {
        return scrollingSpeed;
    }

    public void setScrollingSpeed(float s) {
        scrollingSpeed = s;
    }

}
