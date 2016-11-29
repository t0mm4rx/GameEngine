package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Util.Math;

public class BoxBody extends Component{

    private GameObject go;
    private com.badlogic.gdx.physics.box2d.Body body;
    private float width, height;

    public BoxBody(GameObject go, BodyDef.BodyType bodyType) {
        this.go = go;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        bodyDef.angle = Math.DegreeToRadian(go.getTransform().getRotation());
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        if (go.getSpriteRenderer() != null) {
            shape.setAsBox(
                    go.getSpriteRenderer().getTexture().getWidth() / 2 * go.getTransform().getScale().x,
                    go.getSpriteRenderer().getTexture().getHeight() / 2 * go.getTransform().getScale().y);
            width = go.getSpriteRenderer().getTexture().getWidth() / 2 * go.getTransform().getScale().x;
            height = go.getSpriteRenderer().getTexture().getHeight() / 2 * go.getTransform().getScale().y;
        } else {
            System.err.println("Warning ! The given gameobject has no sprite renderer, please use BoxBody(GameObject go, float width, float height, BodyDef.BodyType bodyType) constructor.");
            shape.setAsBox(10, 10);
            width = 10;
            height = 10;
        }

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public BoxBody(GameObject go, float width, float height, BodyDef.BodyType bodyType) {
        this.go = go;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        bodyDef.angle = Math.DegreeToRadian(go.getTransform().getRotation());
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width / 2 * go.getTransform().getScale().x, height / 2* go.getTransform().getScale().y);
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public void render() {

    }

    public void renderInHUD() {

    }

    public void update() {
        go.getTransform().setPosition(body.getPosition());
        go.getTransform().setRotation(Math.RadianToDegree(body.getAngle()));
    }

    public Vector2 getSize() {
        return new Vector2(width, height);
    }
    public Body getBody() {
        return body;
    }

}
