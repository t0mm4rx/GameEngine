package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;

import java.util.ArrayList;

import box2dLight.RayHandler;

public abstract class Screen implements com.badlogic.gdx.Screen {

    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> hud;
    public OrthographicCamera camera;
    protected Game game;
    protected RayHandler rayHandler;

    public Screen (Game game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        gameObjects = new ArrayList<GameObject>();
        hud = new ArrayList<GameObject>();
    }

    public GameObject getGameObjectByClass(String className) {
        for (GameObject go : gameObjects) {
            if (go.getClass().getSimpleName().equals(className)) {
                return go;
            }
        }
        System.err.println("Warning : trying to get an non-existing component.");
        return null;
    }

    public ArrayList<GameObject> getGameObjectsByClass(String className) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (GameObject go : gameObjects) {
            if (go.getClass().getSimpleName().equals(className)) {
                gos.add(go);
            }
        }
        return gos;
    }

    public ArrayList<GameObject> getGameObjectsByTag(String tag) {
        ArrayList<GameObject> gos = new ArrayList<GameObject>();
        for (GameObject go : gameObjects) {
            if (go.getTag().equals(tag)) {
                gos.add(go);
            }
        }
        return gos;
    }

    public void setScreen(Screen screen) {
        game.setScreen(screen);
    }

    public void addGameObject(GameObject go) {
        this.gameObjects.add(go);
    }

    public void addGameObjectInHUD(GameObject go) {
        this.hud.add(go);
    }

    public void render (float delta) {

        camera.update();
        Game.batch.setProjectionMatrix(camera.combined);
        if (rayHandler != null) {
            rayHandler.setCombinedMatrix(camera);
        }
        for (String layout : Game.getLayouts()) {
            for (GameObject go : gameObjects) {
                if (go.getLayout().equals(layout)) {
                    go.render();
                    go.update();
                }
            }
        }

        for (GameObject go : gameObjects) {
            if (go.getLayout() == "") {
                go.render();
                go.update();
            }
        }

        if (rayHandler != null) {
            rayHandler.updateAndRender();
        }
        update();


    }

    public void renderHUD() {
        for (GameObject go : hud) {
            go.renderInHUD();
            go.update();
        }
    }

    public void activateLights() {
        rayHandler = new RayHandler(Game.world);
        rayHandler.setCombinedMatrix(camera);
    }

    public abstract void update();

    public void resize(int width, int height) {

    }

    public void pause() {

    }

    public void resume() {

    }

    public void hide() {

    }

    public void dispose() {
        rayHandler.dispose();
    }

}
