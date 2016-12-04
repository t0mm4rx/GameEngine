package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.ArrayList;

import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.MapReader;

public class LD37Warmup extends Screen {

    Player player;

    public LD37Warmup(Game game) {
        super(game);
    }

    public void show() {
        world.setGravity(new Vector2(0, 0));

        ArrayList<EmptyGameObject> map = MapReader.read(Gdx.files.internal("map.map"));

        for (EmptyGameObject go : map) {
            add(go);
        }

        player = new Player(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        add(player);
    }

    public void update() {
        if (Gdx.input.isKeyJustPressed(Input.Keys.D)) {
            Game.debugging = !Game.debugging;
        }
    }

}
