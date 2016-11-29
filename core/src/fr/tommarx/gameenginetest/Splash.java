package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import java.util.concurrent.Callable;

import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Util.WaitAndDo;

public class Splash extends Screen {

    EmptyGameObject text;

    public Splash(Game game) {
        super(game);
    }

    public void show() {
        Game.debugging = true;
        text = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        text.addComponent(new Text(text, "Tom Marx", Color.WHITE));
        add(text);
        ((Text) text.getComponentByClass("Text")).getColor().a = 0f;
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 0f, 1f, 3f, 0f, false));
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 1f, -1f, 3f, 3f, false));
        Game.waitAndDo(6, new Callable() {
            public Object call() throws Exception {
                setScreen(new ScreenTest(game));
                return null;
            }
        });
    }

    public void update() {
        ((Text) text.getComponentByClass("Text")).getColor().a = Game.tweenManager.getValue("Alpha");
    }
}
