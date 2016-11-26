package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;

public class GameClass extends Game {

	public void create () {
		init();
		setScreen(new ScreenTest());
	}

	
	public void dispose () {
		stop();
	}
}
