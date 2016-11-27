package fr.tommarx.gameengine.Game;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;

import java.util.ArrayList;

import fr.tommarx.gameengine.Easing.TweenManager;


public abstract class Game extends com.badlogic.gdx.Game {

    public static SpriteBatch batch;
    public static SpriteBatch HUDbatch;
    public static World world;
    public static boolean debugging;
    private Box2DDebugRenderer colliderRenderer;
    private static String[] debugInfos;
    private BitmapFont font;
    private static ArrayList<String> layouts;
    private static Screen screen;
    public static TweenManager tweenManager;

    public Game() {}

    protected void init() {
        debugging = false;
        debugInfos = new String[10];
        colliderRenderer = new Box2DDebugRenderer();
        batch = new SpriteBatch();
        HUDbatch = new SpriteBatch();
        world = new World(new Vector2(0, -98f), true);
        font = new BitmapFont();
        layouts = new ArrayList<String>();
        tweenManager = new TweenManager();
    }

    public void render() {
        world.step(Gdx.graphics.getDeltaTime(), 6, 2);
        tweenManager.update();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        super.render();
        batch.end();
        HUDbatch.begin();
        if (debugging) {
            for (int i = 0; i < debugInfos.length; i++) {
                if (debugInfos[i] != null) {
                    font.draw(HUDbatch, debugInfos[i], 2 , Gdx.graphics.getHeight() - (i + 1) * 15);
                }
            }
        }
        screen.renderHUD();
        HUDbatch.end();


        batch.begin();
        if (debugging) {
            colliderRenderer.render(world, batch.getProjectionMatrix().cpy());
        }
        batch.end();

        debugInfos = new String[10];

    }

    protected void stop() {
        batch.dispose();
    }


    public static void debug(int line, String text) {
        if (line > 0 && line < 11) {
            debugInfos[line - 1] = text;
        } else {
            System.out.println("Error with debug fonction ! Line must be > 0 and <= 10");
        }
    }

    public static void setGravity(Vector2 gravity) {
        world.setGravity(gravity);
    }

    public static void addLayout(String name, int z) {
        layouts.add(z, name);
    }

    public static ArrayList<String> getLayouts() {
        return layouts;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
        super.setScreen(screen);
    }

    public static Screen getCurrentScreen() {
        return screen;
    }

}
