package fr.tommarx.gameenginetest;

import fr.tommarx.gameengine.Game.Game;

public class GameClass extends Game {

	public void create () {
		init();
		setScreen(new LD37Warmup(this));
	}

	
	public void dispose () {
		stop();
	}
}
