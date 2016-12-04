package fr.tommarx.gameenginetest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.Screen;
import fr.tommarx.gameengine.UI.UICanvas;

public class MenuTest extends Screen {

    UICanvas ui;

    public MenuTest(Game game) {
        super(game);
    }

    public void update() {

    }

    public void show() {
        ui = new UICanvas(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), 0, 0, Gdx.files.internal("testskin/uiskin.json"));
        ui.addUIComponent(new TextField("", ui.getSkin()));
        ui.addUIComponent(new TextButton("Play !", ui.getSkin()));
        add(ui);
    }
}
