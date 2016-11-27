package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;


public class LightScreen extends Screen {

    public LightScreen(Game game) {
        super(game);
    }

    public void show() {
        activateLights();
        rayHandler.setAmbientLight(0.3f, 0.3f, 0.7f, 0.1f);

    }

    public void update() {

    }

}
