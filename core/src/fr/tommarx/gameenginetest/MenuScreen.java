package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Event;
import com.badlogic.gdx.scenes.scene2d.EventListener;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.concurrent.Callable;

import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.UI.UICanvas;
import fr.tommarx.gameengine.Util.WaitAndDo;

public class MenuScreen extends Screen{

    UICanvas canvas;

    public MenuScreen(Game game) {
        super(game);
    }


    TextButton button;

    public void show() {
        //Game.debugging = true;
        canvas = new UICanvas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, Gdx.files.internal("testskin/uiskin.json"));
        button = new TextButton("Play !", canvas.getSkin());
        canvas.getTable().add(new Label("Pseudo :", canvas.getSkin()));
        canvas.getTable().row();
        canvas.getTable().add(new TextField("", canvas.getSkin())).width(100);
        canvas.getTable().row();
        canvas.getTable().add(button);

        button.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                Game.tweenManager.goTween(new Tween("AlphaMenu", Tween.LINEAR_EASE_INOUT, 0f, 1f, 2f, 0f, false));
                Game.waitAndDo(2f, new Callable() {
                    public Object call() throws Exception {
                        setScreen(new LightScreen(game));
                        return null;
                    }
                });
            }
        });

        add(canvas);
    }

    public void update() {

    }

}
