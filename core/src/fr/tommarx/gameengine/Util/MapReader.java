package fr.tommarx.gameengine.Util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.BodyDef;

import java.util.ArrayList;

import fr.tommarx.gameengine.Components.BoxBody;
import fr.tommarx.gameengine.Components.BoxRenderer;
import fr.tommarx.gameengine.Components.CircleBody;
import fr.tommarx.gameengine.Components.SpriteRenderer;
import fr.tommarx.gameengine.Components.Text;
import fr.tommarx.gameengine.Components.Transform;
import fr.tommarx.gameengine.Game.EmptyGameObject;

public class MapReader {

    public static ArrayList<EmptyGameObject> read(FileHandle file) {
        ArrayList<EmptyGameObject> go = new ArrayList<EmptyGameObject>();

        for (String gameobject : getGameObjects(file.readString())) {

            Transform t;

            if (getPropertie(gameobject, "rotation") != "") {
                t = new Transform(new Vector2(Float.parseFloat(getPropertie(gameobject, "x")), Float.parseFloat(getPropertie(gameobject, "y"))), new Vector2(1, 1), Float.parseFloat(getPropertie(gameobject, "rotation")));
            } else {
                t = new Transform(new Vector2(Float.parseFloat(getPropertie(gameobject, "x")), Float.parseFloat(getPropertie(gameobject, "y"))));
            }

            EmptyGameObject object = new EmptyGameObject(t);

            if (getPropertie(gameobject, "tag") != "") {
                object.setTag(getPropertie(gameobject, "tag"));
            }

            if (getPropertie(gameobject, "layout") != "") {
                object.setLayout(Integer.parseInt(getPropertie(gameobject, "layout")));
            }

            if (getPropertie(gameobject, "scrollingSpeed") != "") {
                object.setScrollingSpeed(Float.parseFloat(getPropertie(gameobject, "scrollingSpeed")));
            }

            for (String component : getComponents(gameobject)) {
                String type = getComponentPropertie(component, "type");
                if (type.equals("SpriteRenderer")) {
                    object.addComponent(new SpriteRenderer(object, Gdx.files.internal(getComponentPropertie(component, "image"))));
                }
                if (type.equals("BoxBody")) {
                    BodyDef.BodyType body = BodyDef.BodyType.StaticBody;
                    String bodyString = getComponentPropertie(component, "bodyType");
                    if (bodyString.equals("Static")) {
                        body = BodyDef.BodyType.StaticBody;
                    }
                    if (bodyString.equals("Dynamic")) {
                        body = BodyDef.BodyType.DynamicBody;
                    }
                    if (bodyString.equals("Kinematic")) {
                        body = BodyDef.BodyType.KinematicBody;
                    }
                    object.addComponent(new BoxBody(object, Float.parseFloat(getComponentPropertie(component, "width")), Float.parseFloat(getComponentPropertie(component, "height")), body));
                }
                if (type.equals("CircleBody")) {
                    BodyDef.BodyType body = BodyDef.BodyType.StaticBody;
                    String bodyString = getComponentPropertie(component, "bodyType");
                    if (bodyString.equals("Static")) {
                        body = BodyDef.BodyType.StaticBody;
                    }
                    if (bodyString.equals("Dynamic")) {
                        body = BodyDef.BodyType.DynamicBody;
                    }
                    if (bodyString.equals("Kinematic")) {
                        body = BodyDef.BodyType.KinematicBody;
                    }
                    object.addComponent(new CircleBody(object, Float.parseFloat(getComponentPropertie(component, "radius")), body));
                }
                if (type.equals("Text")) {
                    object.addComponent(new Text(object, Gdx.files.internal(getComponentPropertie(component, "fontFile")), Integer.parseInt(getComponentPropertie(component, "size")), getComponentPropertie(component, "text"),
                            new Color(Float.parseFloat(getComponentPropertie(component, "r")),
                                    Float.parseFloat(getComponentPropertie(component, "g")),
                                    Float.parseFloat(getComponentPropertie(component, "b")),
                                    Float.parseFloat(getComponentPropertie(component, "a")))));
                }
                if (type.equals("BoxRenderer")) {
                    object.addComponent(new BoxRenderer(object, Float.parseFloat(getComponentPropertie(component, "width")), Float.parseFloat(getComponentPropertie(component, "height")), new Color(Float.parseFloat(getComponentPropertie(component, "r")),
                            Float.parseFloat(getComponentPropertie(component, "g")),
                            Float.parseFloat(getComponentPropertie(component, "b")),
                            Float.parseFloat(getComponentPropertie(component, "a")))));
                }
            }

            go.add(object);
        }

        return go;
    }

    private static String[] getGameObjects (String file) {
        return file.split("\n");
    }

    private static String getPropertie(String go, String prop) {
        String[] props = go.split(",");
        for (String p : props) {
            if (p.split(":")[0].equals(prop)) {
                return p.split(":")[1];
            }
        }

        return "";
    }

    private static String[] getComponents(String go) {
        return getPropertie(go, "components").split("--");
    }

    private static String getComponentPropertie (String component, String prop) {
        String[] props = component.split("-");
        for (String p : props) {
            if (p.split("=")[0].equals(prop)) {
                return p.split("=")[1];
            }
        }

        return "";
    }


}
