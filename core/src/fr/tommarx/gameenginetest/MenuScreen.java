package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.UI.UICanvas;

public class MenuScreen extends Screen{

    UICanvas canvas;

    public MenuScreen(Game game) {
        super(game);
    }


    public void show() {
        canvas = new UICanvas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, Gdx.files.internal("testskin/uiskin.json"));
        canvas.addUIComponent(new TextButton("Play !", canvas.getSkin()));
        canvas.addUIComponent(new TextField("Textfllied", canvas.getSkin()));
        canvas.addUIComponent(new TextButton("Play !", canvas.getSkin()));
        addUICanvas(canvas);
    }

    public void update() {

    }

}
