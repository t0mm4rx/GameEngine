package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;

import java.util.concurrent.Callable;

import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Easing.Tween;
import fr.tommarx.gameengine.Game.EmptyGameObject;
import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.UI.UICanvas;

public class MenuScreen extends Screen{

    UICanvas canvas;
    EmptyGameObject overlay;

    public MenuScreen(Game game) {
        super(game);
    }


    TextButton button;

    public void show() {
        Game.debugging = true;
        canvas = new UICanvas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, Gdx.files.internal("testskin/uiskin.json"));
        button = new TextButton("Play !", canvas.getSkin());
        canvas.getTable().add(new Label("Pseudo :", canvas.getSkin()));
        canvas.getTable().row();
        canvas.getTable().add(new TextField("", canvas.getSkin())).width(100);
        canvas.getTable().row();
        canvas.getTable().add(button);

        overlay = new EmptyGameObject(new Transform(new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2)));
        overlay.addComponent(new BoxRenderer(overlay, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), new Color(0, 0, 0, 0)));
        overlay.setLayout(1);
        add(overlay);

        button.addListener(new ChangeListener() {

            public void changed(ChangeEvent event, Actor actor) {
                Game.tweenManager.goTween(new Tween("AlphaMenu", Tween.LINEAR_EASE_NONE, 0f, 1f, 1f, 0f, false));
                Game.waitAndDo(1f, new Callable() {
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
        Game.debug(1, ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).getColor().a + "");
        ((BoxRenderer) overlay.getComponentByClass("BoxRenderer")).setColor(new Color(0, 0, 0, Game.tweenManager.getValue("AlphaMenu")));
    }

}
