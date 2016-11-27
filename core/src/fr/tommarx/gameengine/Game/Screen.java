package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import box2dLight.RayHandler;

public abstract class Screen implements com.badlogic.gdx.Screen {

    protected ArrayList<GameObject> gameObjects;
    protected ArrayList<GameObject> hud;
    public OrthographicCamera camera;
    protected Game game;
    protected RayHandler rayHandler;
    public World world;
    private Box2DDebugRenderer colliderRenderer;

    public Screen (Game game) {
        this.game = game;
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        camera.position.set(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2, 0);
        gameObjects = new ArrayList<GameObject>();
        hud = new ArrayList<GameObject>();
        world = new World(new Vector2(0, -98f), true);
        colliderRenderer = new Box2DDebugRenderer();
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
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
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

        //Game.batch.begin();
        if (Game.debugging) {
            colliderRenderer.render(world, Game.batch.getProjectionMatrix().cpy());
        }
        //Game.batch.end();

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
        rayHandler = new RayHandler(world);
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
