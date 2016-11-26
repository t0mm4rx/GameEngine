package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.Easing.Tween;

public class Splash extends Screen {

    EmptyGameObject text;

    public void show() {
        Game.debugging = true;
        text = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        text.addComponent(new Text(text, "Tom Marx", Color.WHITE));
        addGameObject(text);
        ((Text) text.getComponentByClass("Text")).getColor().a = 0f;
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 0f, 1f, 3f, 0f));
        Game.tweenManager.goTween(new Tween("Alpha", Tween.LINEAR_EASE_NONE, 1f, -1f, 3f, 3f));
        Game.tweenManager.goTween(new Tween("Y", Tween.CUBE_EASE_INOUT, 0f, 1f, 2f, 0f));
    }

    public void update() {
        Game.debug(1, ((Text) text.getComponentByClass("Text")).getColor().a + "");
        ((Text) text.getComponentByClass("Text")).getColor().a = Game.tweenManager.getValue("Alpha");
        text.getTransform().getPosition().y = Game.tweenManager.getValue("Y") * Gdx.graphics.getHeight() / 2;
    }
}
