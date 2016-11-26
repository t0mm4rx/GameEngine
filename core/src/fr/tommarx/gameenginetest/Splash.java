package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Util.Tween;

public class Splash extends Screen {

    EmptyGameObject text;

    public void show() {
        Game.debugging = true;
        text = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        text.addComponent(new Text(text, "Tom Marx", Color.WHITE));
        //text.addComponent(new SpriteRenderer(text, Gdx.files.internal("badlogic.jpg")));
        addGameObject(text);
        Game.tweenManager.goTween(new Tween(Interpolation.fade, 0.2f, 0.5f, 2f, 1f), 0);
    }

    public void update() {
        Game.debug(1, ((Text) text.getComponentByClass("Text")).getColor().a + "");
        ((Text) text.getComponentByClass("Text")).getColor().a = Game.tweenManager.getValue(0);
    }
}
