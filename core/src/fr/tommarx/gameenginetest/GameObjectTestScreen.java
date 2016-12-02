package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;

public class GameObjectTestScreen extends Screen {

    EmptyGameObject player;

    public GameObjectTestScreen(Game game) {
        super(game);
    }

    public void show() {
        player = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));

        SpriteRenderer sprite1 = new SpriteRenderer(player, Gdx.files.internal("Badlogic.jpg"));
        sprite1.setLayout(0);
        player.addComponent(sprite1);

        SpriteRenderer sprite2 = new SpriteRenderer(player, Gdx.files.internal("torch.gif"));
        sprite2.setOffset(100, 110);
        sprite2.setLayout(1);
        player.addComponent(sprite2);

        SpriteRenderer sprite3 = new SpriteRenderer(player, Gdx.files.internal("Badlogic.jpg"));
        sprite3.setLayout(5);
        sprite3.setOffset(-30, -30);
        player.addComponent(sprite3);

        add(player);

    }

    public void update() {

    }

}
