package fr.tommarx.gameengine.Components;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;

import fr.tommarx.gameengine.Game.Game;
import fr.tommarx.gameengine.Game.GameObject;
import fr.tommarx.gameengine.Util.Math;

public class CircleBody extends Component{

    private GameObject go;
    private com.badlogic.gdx.physics.box2d.Body body;
    private float radius;

    public CircleBody(GameObject go, BodyDef.BodyType bodyType) {
        this.go = go;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        bodyDef.angle = Math.DegreeToRadian(go.getTransform().getRotation());
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        if (go.getSpriteRenderer() != null) {
            shape.setRadius(go.getSpriteRenderer().getTexture().getWidth() / 2 * go.getTransform().getScale().x);
            radius = go.getSpriteRenderer().getTexture().getWidth() / 2 * go.getTransform().getScale().x;
        } else {
            System.err.println("Warning ! The given gameobject has no sprite renderer, please use CircleBody(GameObject go, float radius, BodyDef.BodyType bodyType) constructor.");
            shape.setRadius(10);
            radius = 10;
        }
        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = 1f;
        body.createFixture(fixtureDef);
        shape.dispose();
    }

    public CircleBody(GameObject go, float radius, BodyDef.BodyType bodyType) {
        this.go = go;
        this.radius = radius;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = bodyType;
        bodyDef.position.set(go.getTransform().getPosition().x, go.getTransform().getPosition().y);
        bodyDef.angle = Math.DegreeToRadian(go.getTransform().getRotation());
        body = Game.getCurrentScreen().world.createBody(bodyDef);
        CircleShape shape = new CircleShape();
        shape.setRadius(radius);
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

    public float getRadius() {
        return radius;
    }
    public Body getBody() {
        return body;
    }

}
