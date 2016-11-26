package fr.tommarx.gameengine.Game;

import com.badlogic.gdx.Gdx;

import java.util.ArrayList;
import java.util.UUID;

import fr.tommarx.gameengine.Components.Component;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;

public abstract class GameObject {

    private String id, tag;
    private ArrayList<Component> components;
    private String layout;

    public GameObject(Transform transform) {

        //Generate UUID
        id = UUID.randomUUID().toString();

        tag = "";
        layout = "";
        components = new ArrayList<Component>();

        //Adding a transform component
        this.addComponent(transform);

    }

    public String getUUID() {
        return id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public void addComponent(Component comp) {
        this.components.add(comp);
    }

    public Component getComponentByClass(String className) {
        for (Component comp : components) {
            if (comp.getClass().getSimpleName().equals(className)) {
                return comp;
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public ArrayList<Component> getComponentsByClass(String className) {
        ArrayList<Component> comps = new ArrayList<Component>();
        for (Component comp : components) {
            if (comp.getClass().getSimpleName().equals(className)) {
                comps.add(comp);
            }
        }
        return comps;
    }

    public Transform getTransform() {
        return (Transform)getComponentByClass("Transform");
    }

    public SpriteRenderer getSpriteRenderer() {
        return (SpriteRenderer)getComponentByClass("SpriteRenderer");
    }

    public void render() {
        for (Component comp : components) {
            comp.render();
        }
    }

    public void renderInHUD() {
        for (Component comp : components) {
            comp.renderInHUD();
        }
    }

    public void update() {
        for (Component comp : components) {
            comp.update();
        }
        update(Gdx.graphics.getDeltaTime());
    }

    public void setLayout(String layout) {
        this.layout = layout;
    }

    public String getLayout() {
        return layout;
    }

    protected abstract void update(float delta);

}