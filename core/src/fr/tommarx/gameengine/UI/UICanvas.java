package fr.tommarx.gameengine.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.FitViewport;

import fr.tommarx.gameengine.Game.Drawable;
import fr.tommarx.gameengine.Game.Game;

public class UICanvas extends Drawable{

    private Stage stage;
    private Table table;
    private Skin skin;

    public UICanvas(float width, float height, float x, float y) {
        stage = new Stage(new FitViewport(width, height));
        stage.getViewport().setScreenX((int) x);
        stage.getViewport().setScreenX((int) y);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(Game.debugging);

        skin = new Skin();

        Gdx.input.setInputProcessor(stage);
    }

    public UICanvas(float width, float height, float x, float y, FileHandle skin) {
        stage = new Stage(new FitViewport(width, height));
        stage.getViewport().setScreenX((int) x);
        stage.getViewport().setScreenX((int) y);
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        table.setDebug(true);

        this.skin = new Skin(skin);

        Gdx.input.setInputProcessor(stage);
    }

    public Skin getSkin() {
        return skin;
    }

    public Table getTable() {
        return table;
    }

    public void addUIComponent(Actor a) {
        table.addActor(a);
    }

    public void render() {
        table.setDebug(Game.debugging);
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }

    public void renderInHUD() {

    }

    public void update() {}

    public void dispose() {
        stage.dispose();
    }

}
